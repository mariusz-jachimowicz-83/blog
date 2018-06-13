# Blog


### Pre

* install Java
* install Leiningen
* install node.js + npm + yarn
* install node-gyp `apt-get install node-gyp` if there are errors

## Develop

```bash
# install deps
yarn 

# run development
yarn watch
```

To start styling

```sh
lein sass4clj auto
```

To build styles once

```sh
lein sass4clj once
```

## Develop (old, based on ClojureScript compiler and Figwheel)

#### CLJS REPL

If you want sent CLJS commands one by one directy from editor to change UI state you can start CLJS REPL

```clojure
dev=> (cljs-repl)
```

## Legal

Copyright © 2018 FIXME
