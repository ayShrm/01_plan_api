package com.ayush.service;

import java.util.List;
import java.util.Map;

import com.ayush.entity.Plan;

public interface PlanService {

	public Map<Integer, String> getPlanCategories();

	public boolean savePlan(Plan plan);

	public boolean updatePlan(Plan plan);

	public List<Plan> getAllPlans();

	public Plan getPlanbyId(Integer planId);

	public boolean deletePlanById(Integer planId);

	public boolean softDelete(Integer planId, String activeSw);
}
