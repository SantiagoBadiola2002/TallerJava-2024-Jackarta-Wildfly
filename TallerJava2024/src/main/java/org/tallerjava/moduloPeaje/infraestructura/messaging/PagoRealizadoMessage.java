package org.tallerjava.moduloPeaje.infraestructura.messaging;

import java.io.StringReader;
import java.io.StringWriter;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonParser;

import java.io.BufferedOutputStream;
import java.io.Writer;

public record PagoRealizadoMessage(
        int tag,
        String matricula,
        int nacionalidad
) {
    /**
		* librería de jakarta para trabajar con dt convertidos a json 
     * @return
     */
    public String toJson() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("tag", this.tag)
                .add("matricula", this.matricula)
                .add("nacionalidad", this.nacionalidad).build();

       StringWriter sw = new StringWriter();
       JsonWriter jsonWriter = Json.createWriter(sw);
       jsonWriter.write(jsonObject);
       jsonWriter.close();
       return sw.toString();
    }

    public static PagoRealizadoMessage buildFromJson(String jsonPagoRealizada) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonPagoRealizada));
        JsonObject objeto = jsonReader.readObject();
        System.out.println("acaaa"); 
        PagoRealizadoMessage pago = new PagoRealizadoMessage(
                objeto.getInt("tag"),
                objeto.getString("matricula"),
                objeto.getInt("nacionalidad"));
        return pago;
    }
}