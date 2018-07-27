package com.teamproject.trainingrequest.service;

import com.teamproject.trainingrequest.entity.TrainingRequestEntity;
import com.teamproject.trainingrequest.model.Employee;
import com.teamproject.trainingrequest.model.CreateTrainingRequest;
import com.teamproject.trainingrequest.repository.TrainingRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TrainingRequestService implements RequestService {

    private final ModelMapper modelMapper;
    private TrainingRequestRepository repo;
    private EmployeeService employeeService;

    public TrainingRequestService(TrainingRequestRepository repo, EmployeeService employeeService) {
        this.repo = repo;
        this.employeeService = employeeService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Long createTrainingRequest(CreateTrainingRequest createTrainingRequest) {
        Employee employee = employeeService.getEmployeeById(createTrainingRequest.getEmployeeId());
        TrainingRequestEntity entity = modelMapper.map(createTrainingRequest, TrainingRequestEntity.class);
        entity.setRequestedByFirstName(employee.getFirstName());
        entity.setRequestedByLastName(employee.getLastName());
        TrainingRequestEntity saved = repo.save(entity);

        return saved.getId();
    }
}
