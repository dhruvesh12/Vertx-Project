package com.dhruvesh.khidake.DhruveshTest;

import io.vertx.core.AbstractVerticle;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class MongoConnection extends AbstractVerticle {



  public PgPool getPgPool() {
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
