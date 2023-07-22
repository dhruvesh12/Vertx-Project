package com.dhruvesh.khidake.DhruveshTest.PostResponse;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgPool;

public class PostResponse {
  public static void attach(Router router, PgPool pg, Vertx vertx) {


    router.get("/PostResponse").handler(ctx->{

      ctx.response().end("This is POST Response");
    });
  }
}
