package com.dhruvesh.khidake.DhruveshTest;

import io.vertx.core.*;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  private final static Logger Log = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();





    vertx.exceptionHandler(error->{
<<<<<<< HEAD
      Log.error("Something Went Wrong 1 With Main {}",error.getCause());
=======
      Log.error("Something Went Wrong 2 With Main {}",error.getCause());
>>>>>>> Dhruvesh
    });

    vertx
      .deployVerticle(new MainVerticle())
      .onFailure(error->{
        Log.error("Failed to Deploy {}",error);
      })
      .onSuccess(ctx->{
        Log.info("successfully Deployed vertical");

      });








  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {



    final DeploymentOptions deploymentOptionsAuth = new DeploymentOptions().setWorkerPoolName("mypool")
      .setWorkerPoolSize(20).setWorker(true).setInstances(getInstances());

    vertx.deployVerticle(RestApi.class.getName(),deploymentOptionsAuth)
      .onFailure(startPromise::fail)
      .onSuccess(ctx->{
        Log.info("Sucessfully Deployed {}",ctx.getClass().getSimpleName());
        startPromise.complete();
      });
  }

  private static int getInstances() {
    return Math.max(1,Runtime.getRuntime().availableProcessors());
  }


}
