package com.dhruvesh.khidake.DhruveshTest;


import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
public class Student {

  String name;


  public JsonObject toJsonObject() {
    return JsonObject.mapFrom(this);
  }


}
