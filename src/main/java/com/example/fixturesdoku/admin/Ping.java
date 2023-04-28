package com.example.fixturesdoku.admin;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@Path("/ping")
public class Ping {
    @GET @Query public @NonNull String ping() {return "pong";}
}
