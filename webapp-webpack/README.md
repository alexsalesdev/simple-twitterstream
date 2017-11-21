Play! Framework with Webpack & Angular
======================================

Update 2017-07-02
-----------------

I updated several pieces:

* Play `2.6.0`
* Scala `2.12.2`
* TypeScript `2.4.1`
* Webpack `3.0.0`
* Angular `4.2.5`
* RxJS `5.4.1`

I also changed the target for transpiled JS to ES6. If you want to use ES5 you will need to add some extra polyfills.

There is an known issue related to RxJS and TypeScript (<https://github.com/ReactiveX/rxjs/issues/2539>):

`error TS2415: Class 'Subject<T>' incorrectly extends base class 'Observable<T>'`

About
-----

The idea of this project is to show a basic Play! application integrated with Webpcak for development.
You can use it as a seed or just copy an paste code fragments into your own project.
  
When using SBT `run` command, a Node server for Webpack will be also started on port 8080. You can change this in
the file `webpack.server.js`.

The TypeScript/JavaScript application is intended to be placed in the path `./app/frontend` of this project. This code 
will be _transpiled_ into a bundle file under `./public/bundles` that will be included in the final Play! package when 
using `sbt dist`.

As this is intended to be a single page application all HTML templates have been removed from Play! and all controllers
are used to serve static/Webpack files. You can extend them to build a REST API to be used by the JavaScript app.

The single page for the web application is under `./public/index.html`.

The TypeScript entry point is under `./app/frontend/app/main.ts`.

## Running NPM and other related commands

All Node modules are installed under `./app/frontend/node_modules` and you should run `npm` commands under `./app/frontend`.
Other commands as `typings` (the new version of `tsd`).

## Setting up your environment

To install TS/JS dependencies, including Angular and TypeScript, use `npm install`


## Build Play Package

This process will also transpile the single web app into JavaScript bundles using Webpack.

Use `sbt dist`.

This will create a Universal package (zip file) under `target/universal/`.

To test this package, unzip it and run `.../bin/package_name`.


## Running Play simulating production (using transpiled JavaScript)

Use `sbt run`

This will launch Webpack server (a NodeJS server running webpack) and Play! server.

Use `ctrl + C` to stop the server. This will stop both servers.

If you use `ctrl + D` to stop the process, this will only stop SBT and Play! server and you will need to terminate
the Webpack server manually. One way to do this is: locate the process with `ps -x | grep webpack` and
then `kill -9 [processNumber]` using the number returned by the previous command.

## Dependencies

* SBT
* Play
* Scala
* Node
* TypeScript
* Webpack
* Angular
* RxJS

## TODO

Add Test configuration for both, Scala and JavaScript