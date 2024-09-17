package service;

import dao.TimeDAO;
import model.Time;
import spark.Request;
import spark.Response;

import java.util.List;

public class TimeService {
    private TimeDAO timeDAO;

    public TimeService() {
        this.timeDAO = new TimeDAO();
    }

    public String add(Request request, Response response) {
        String nome = request.queryParams("nome");
        int torcida = Integer.parseInt(request.queryParams("torcida"));
        int titulos = Integer.parseInt(request.queryParams("titulos"));

        Time time = new Time();
        time.setNome(nome);
        time.setTorcida(torcida);
        time.setTitulos(titulos);

        boolean status = timeDAO.insert(time);
        response.status(status ? 201 : 500); 
        return status ? "Time adicionado com sucesso!" : "Erro ao adicionar o time.";
    }

    public Time get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Time time = timeDAO.get(id);
        
        if (time == null) {
            response.status(404); 
            return null;
        }
        
        return time;
    }

    public String update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        String nome = request.queryParams("nome");
        int torcida = Integer.parseInt(request.queryParams("torcida"));
        int titulos = Integer.parseInt(request.queryParams("titulos"));

        Time time = new Time(id, nome, torcida, titulos);
        boolean status = timeDAO.update(time);

        response.status(status ? 200 : 500); 
        return status ? "Time atualizado com sucesso!" : "Erro ao atualizar o time.";
    }

    public String remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        boolean status = timeDAO.delete(id);

        response.status(status ? 200 : 500); 
        return status ? "Time removido com sucesso!" : "Erro ao remover o time.";
    }

    public List<Time> getAll(Request request, Response response) {
        List<Time> times = timeDAO.get();
        return times;
    }
}
