package com.ayush.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.constants.AppConstants;
import com.ayush.entity.Plan;
import com.ayush.props.AppProperties;
import com.ayush.service.PlanService;

@RestController
public class PlanRestController {

	private PlanService planService;

	private Map<String, String> messages;

	public PlanRestController(PlanService planService, AppProperties appProps) {
		this.planService = planService;
		this.messages = appProps.getMessages();
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> planCategories = planService.getPlanCategories();
		return new ResponseEntity<>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String msg = AppConstants.EMPTY_STR;
		boolean savePlan = planService.savePlan(plan);
		if (savePlan)
			msg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		else
			msg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean getPlanbyId = planService.updatePlan(plan);
		String msg = AppConstants.EMPTY_STR;
		if (getPlanbyId)
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		else
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanbyId(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean getPlanbyId = planService.deletePlanById(planId);
		String msg = AppConstants.EMPTY_STR;
		if (getPlanbyId)
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		else
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean softDelete = planService.softDelete(planId, status);
		String msg = AppConstants.EMPTY_STR;
		if (softDelete)
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		else
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
