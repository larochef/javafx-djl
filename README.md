# javafx-djl

This project is here to demonstrate some error that I face on my computer.
It happens on linux x64 (archlinux), using the cpu flavor for pytorch.

The model used is `prajjwal1/bert-tiny` but I also got the same issue with other berts.
It was exported using tracing.

This program consists in a simple javafx app that will initialize PyTorch and do a few predictions.

to run the app, type in the console:

`./gradlew run`
