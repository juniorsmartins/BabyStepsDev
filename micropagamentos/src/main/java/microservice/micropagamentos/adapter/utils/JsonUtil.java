package microservice.micropagamentos.adapter.utils;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);
}

