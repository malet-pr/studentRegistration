package com.react.api.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String BAD_REQUEST = "Error al procesar el pedido";
    public static final String USER_NOT_FOUND = "No existe un usuario con ese nombre";
    public static final String BAD_PASSWORD = "Clave equivocada";



    public List<String> DAYS = new ArrayList<>(Arrays.asList("Mon-Thu", "Tue-Fri", "Wed-Sat", "Mon-Wed-Thu", "Tue-Fri-Sat"));
    public List<String> TIMES = new ArrayList<>(Arrays.asList("9-11 AM", "11-1 AM/PM", "1-3 PM", "3-5 PM", "5-7 PM"));

}
