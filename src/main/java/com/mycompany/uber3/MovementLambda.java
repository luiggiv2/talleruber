/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.uber3;

/**
 *
 * @author luiggiv2
 */
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovementLambda implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        double latA = (double) input.get("latA");
        double lngA = (double) input.get("lngA");
        double latB = (double) input.get("latB");
        double lngB = (double) input.get("lngB");
        int steps = (int) input.get("steps");

        double deltaLat = (latB - latA) / steps;
        double deltaLng = (lngB - lngA) / steps;

        List<Map<String, Double>> points = new ArrayList<>();
        for (int i = 0; i <= steps; i++) {
            Map<String, Double> point = new HashMap<>();
            point.put("lat", latA + deltaLat * i);
            point.put("lng", lngA + deltaLng * i);
            points.add(point);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("points", points);
        return response;
    }
}
