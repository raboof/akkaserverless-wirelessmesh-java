# Akka Serverless - Wireless Mesh Example App

A Java-based example app for [Akka Serverless](https://developer.lightbend.com/docs/akka-serverless/)

Features include:

* Customer locations with wireless mesh devices

## What is this example?

To help you get started with Akka Serverless, we've built some example apps that showcase the capabilities of the platform. This example application mimics a company that uses Akka Serverless to keep track of the wireless mesh devices their customers have installed and the devices connected to the meshes.

In this example app you can interact with the devices, assign them to different rooms in the house, and turn them on or off. To make this example even more interactive, you can add an actual nightlight and switch the lights on or off.

We used the following akkaserverless capabilities: event sourced entity (wirelessmeshdomain.CustomerLocationEntity), 
using an Action to publish to google pubsub (wirelessmesh.PublishingAction and publishing.proto) and using a View (wirelessmesh.CustomerLocationView and customerlocationview.proto) and finally
an Action to connect to the Lifx bulb (wirelessmesh.ToggleNightlightAction and devicecontrol.proto).

## Prerequisites

To build and deploy this example application, you'll need to have:

* An [Akka Serverless account](https://docs.cloudstate.com/getting-started/lightbend-account.html)
* Java 11 or higher installed
* Maven 3.x or higher installed
* The Docker CLI installed
* A [service account](https://cloud.google.com/docs/authentication/production) that can connect to your Google Cloud Pubsub

## Build, Deploy, and Test

### Prepare your Google Could Pubsub

Create a Pub/Sub topic called 'wirelessmesh' under your own Google Cloud account.

To connect Akka Serverless to your Google Cloud Pub/Sub you must authenticate using a service account. To create your [service account](https://cloud.google.com/docs/authentication/production#cloud-console). After creating your service account, you need to download the service account key as a JSON file.

Now use the [akkasls](https://developer.lightbend.com/docs/akka-serverless/getting-started/set-up-development-env.html) command-line tool to give Akka Serverless access to your broker:

```
akkasls project set broker --broker-service gcp-pubsub --gcp-key-file testing-pubsub-310212-fec7d0612927.json
```

### LIFX integration for toggling nightlight

If you have an LIFX bulb and would like it to stand in for a wirelessmesh device and have it light on/off when you toggle the device nightlight, you simply have to:
* Have an operational bulb
* When you create your customer location, be sure to set the access token to the authorizaton token you requested with LIFX.
* When you activate the device in this app, make sure it has the same device id as your bulb.
* More information [here][https://api.developer.lifx.com]

### Build your container

To build your own container, run `mvn -Dnamespace=<namespace> clean install`, substituting `<namespace>` for a docker registry namespace you have write access to (for example your dockerhub username).

This command will create a new Docker image.

The result of the command should be:

```bash
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:31 min
[INFO] Finished at: 2021-01-20T16:20:29-08:00
[INFO] ------------------------------------------------------------------------
```

### Deploy your container

To deploy the container as a service in Akka Serverless, you'll need to:

1. Push the container to a container registry: `docker push -t <registry url>/<registry namespace>/akkaserverless-wirelessmesh-java:latest`
1. Deploy the service in Akka Serverless: `akkasls svc deploy wirelessmesh <registry url>/<registry username>/akkaserverless-wirelessmesh-java:latest`

_The above command will deploy your container to your default project with the name `wirelessmesh`. If you want to have a different name, you can change that._

### Testing your service

To test using Postman.
* First install Postman, [found here](https://www.postman.com)
* Assuming you have deployed to akkaserverless and exposed your service to 'winter-mountain-2372.us-east1.apps.akkaserverless.com'...
* Create a Postman POST request to 'https://winter-mountain-2372.us-east1.apps.akkaserverless.com/wirelessmesh/add-customer-location' with the json body '{"customer_location_id": "my-first-location", "access_token": "my lifx access token if applicable"}'
* You should see a response of '200(OK) {}', this will be the response of any POST
* You can now create a GET request to 'https://winter-mountain-2372.us-east1.apps.akkaserverless.com/wirelessmesh/get-customer-location?customerLocationId=my-first-location'
* You should see a json response containing your customer location and no devices.
* Create a POST request to 'https://winter-mountain-2372.us-east1.apps.akkaserverless.com/wirelessmesh/activate-device' with the body '{"customer_location_id": "my-first-location", "device_id": "my-first-device"}'
* Create a POST requset to 'https://winter-mountain-2372.us-east1.apps.akkaserverless.com/wirelessmesh/assign-room' with the body '{"customer_location_id": "my-first-location", "device_id": "my-first-device", "room": "office"}'
* Create a POST requset to 'https://winter-mountain-2372.us-east1.apps.akkaserverless.com/wirelessmesh/toggle-nightlight' with the body '{"customer_location_id": "my-first-location", "device_id": "my-first-device"}'
* Rerun your get-customer-location request
* You should see a json response with your customer location and a collection of your single device with the room assigned and the nightlight on
* Create a POST request to 'https://winter-mountain-2372.us-east1.apps.akkaserverless.com/wirelessmesh/remove-device' with the body '{"customer_location_id": "my-first-location", "device_id": "my-first-device"}'
* You should see a json response no longer containing any devices
* Create a POST request to 'https://winter-mountain-2372.us-east1.apps.akkaserverless.com/wirelessmesh/remove-customer-location' with the body '{"customer_location_id": "my-first-location"}'
* Rerun your get-customer-location request and you will see a server error since it no longer exists.

## Contributing

We welcome all contributions! [Pull requests](https://github.com/lightbend-labs/akkaserverless-wirelessmesh-java/pulls) are the preferred way to share your contributions. For major changes, please open [an issue](https://github.com/lightbend-labs/akkaserverless-wirelessmesh-java/issues) first to discuss what you would like to change.

## Support

This project is provided on an as-is basis and is not covered by the Lightbend Support policy.

## License

See the [LICENSE](./LICENSE).
