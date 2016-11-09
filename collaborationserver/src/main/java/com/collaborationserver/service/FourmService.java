package com.collaborationserver.service;

import java.util.List;

import com.collaborationserver.model.Fourm;
import com.collaborationserver.model.FourmComments;

public interface FourmService
{
	List<Fourm> findAll();
	Fourm findById(int id);
	void create(Fourm fourm);
	void edit(Fourm fourm);
	void deleteById(int id);
	void postComment(FourmComments comments);
	List<FourmComments> showcomment(int id);
	

}
