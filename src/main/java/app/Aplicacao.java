package app;

import static spark.Spark.*;
import service.TimeService;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        port(6789);
        TimeService timeService = new TimeService();

        post("/time", (request, response) -> timeService.add(request, response));
        get("/time/:id", (request, response) -> timeService.get(request, response));
        put("/time/:id", (request, response) -> timeService.update(request, response));
        delete("/time/:id", (request, response) -> timeService.remove(request, response));
        get("/time", (request, response) -> timeService.getAll(request, response));
    }
}
