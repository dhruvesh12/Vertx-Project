package com.dhruvesh.khidake.DhruveshTest.GetRequest;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgPool;

public class GetRequest {
  public static void attach(Router router, PgPool pg, Vertx vertx) {

    router.get("/getRequest").handler(ctx->{




      var result = "It is Not Hello World, It is Hello Earth";


      ctx.response()
        .end(result);
    });

  }
}
