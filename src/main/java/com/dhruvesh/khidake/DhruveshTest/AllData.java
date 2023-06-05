package com.dhruvesh.khidake.DhruveshTest;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.templates.SqlTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class AllData {

  private final static Logger Log = LoggerFactory.getLogger(AllData.class);
  static final int[] num = {1};
  public static void attach(Router router, PgPool pg, Vertx vertx) {

    router.get("/getall").handler(ctx-> {
      Log.info("This Api Is Called For {}", num[0]);
      int nums = num[0] + 1;
      num[0] = nums;


      vertx.executeBlocking(future->{
        SqlTemplate.forQuery(pg, "SELECT * FROM public.\"Attendance\"")
          .mapTo(Row::toJson)
          .execute(Collections.emptyMap())
          .onFailure(err -> {
            Log.error("Recieved Error", err);
            future.fail(err.getCause());
          })

          .onSuccess(next -> {
            if (!next.iterator().hasNext()) {
              future.complete();
              ctx.response()
                .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
                .end(new JsonObject()
                  .put("message", "No Data available!")
                  .put("path", ctx.normalizedPath())
                  .toBuffer()
                );
              return;

            }else{

              var response = new JsonArray();
              next.forEach(response::add);
              ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .end(response.toBuffer());

            }


          });

      });




// Create the client pool

    });
  }
}
