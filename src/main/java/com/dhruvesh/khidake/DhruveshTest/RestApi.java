package com.dhruvesh.khidake.DhruveshTest;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

import io.vertx.sqlclient.templates.SqlTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class RestApi extends AbstractVerticle {

  private final static Logger Log = LoggerFactory.getLogger(RestApi.class);


  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    StartServer(startPromise);
  }


  private void StartServer(Promise<Void> startPromise) {

    final int[] num = {0};



    final Router router = Router.router(vertx);

    PgPool pg = getPgPool();


    AllData.attach(router,pg,vertx);









    vertx.createHttpServer().requestHandler(router).listen(8000, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        Log.info("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private PgPool getPgPool() {
    final var pool = new PgConnectOptions()
      .setPort(5432)
      .setHost("localhost")
      .setDatabase("Students")
      .setUser("postgres")
      .setPassword("yash12345");

    final var poolOption = new PoolOptions()
      .setMaxSize(4);

    PgPool pg = PgPool.pool(vertx,pool,poolOption);
    return pg;
  }
}
