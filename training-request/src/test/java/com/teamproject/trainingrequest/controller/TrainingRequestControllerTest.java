package com.teamproject.trainingrequest.controller;

import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.service.RequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainingRequestControllerTest {

    @InjectMocks
    TrainingRequestController trainingRequestController;

    @Mock
    RequestService service;

    @Test
    public void createTrainingRequest() {
        TrainingRequest trainingRequest = new TrainingRequest();
        when(service.createTrainingRequest(trainingRequest)).thenReturn(new Long(1234));
        ResponseEntity responseEntity = trainingRequestController.createTrainingRequest(trainingRequest);
        assertNotNull(responseEntity);

        String location = responseEntity.getHeaders().get("location").get(0);
        assertEquals("/trainingrequests/1234", location);
    }
}