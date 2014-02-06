# clj-cas-client

A simple CAS client for Clojure, for use as a middleware with Ring.

## Usage

To install, add this to your project.clj:

```clojure
  :dependencies [[clj-cas-client "0.0.5"]]
```

To wrap a handler with cas:

```clojure
(require '[clj-cas-client.core :refer [cas]]
         '[ring.middleware.session :refer [wrap-session]])

(defn cas-server []
  "https://example.org/cas")

(defn service-name []
  "http://my-current-server.example.org")

;; ... routes ...

(def app (-> routes
             handler/site
             (cas cas-server service-name)
             wrap-session))
```

Note that clj-cas-client depends on the session middleware, so the handler returned by `cas` must be wrapped with `wrap-session`.

This will redirect all requests to the cas server for login, validate the tickets from the cas server, and make sure to add a :username key to the request map.

### Single Sign Out

clj-cas-client can destroy a user's session when the CAS server notifies it that the user has signed out. Follow these steps to enable single sign out:

1. Create a Ring session store explicitly and pass it to `wrap-session`.
2. Wrap the handler returned by `wrap-session` with `wrap-cas-single-sign-out` passing it the same session store.

```clojure
(require '[ring.middleware.session.memory :refer [memory-store]])
(require '[clj-cas-client.single-sign-out :refer [wrap-cas-single-sign-out]])

(def app (let [session-store (memory-store)]
           (-> ...
               (cas cas-server service-name)
               (wrap-session {:store session-store})
               (wrap-cas-single-sign-out session-store)))
```

If single sign out doesn't seem to work, make sure that the CAS server can make a POST request to the URL provided by the `service-name` function.

## License

Copyright (C) 2012 Ola Bini

Distributed under the Eclipse Public License, the same as Clojure.
