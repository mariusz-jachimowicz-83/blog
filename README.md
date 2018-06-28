# Blog

Small blog engine

Clojure and ClojureScript rocks!!!
With `one hack` in my `blog engine prototype` I am able to: 
* store blog posts as an clj files with `hiccup markup`
* send them using transit format into UI
* display them in UI even when some tags represents wrapped external React components :smile:

So win is that:
* I can have all content in github repository
* I can easily change all content (redesign and experiment)
* I don't need any db
* I don't need serialization into json or binary format
* project is small
* I can easily extend project because it's re-frame app
* I don't need to tackle with any php, ruby or node.js blog engines 
* simpler installation (put and run one jar file)
* I can easily go from simple blog into website for selling online courses :smile: in the future
* I can use React external components in the content

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

## Legal

Copyright © 2018 FIXME
