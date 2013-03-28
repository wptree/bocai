package com.bocai.dao;

import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Dialog;

public interface DialogDao extends BaseDao<Dialog> {

	public Pagination getDialogDetail(Long sourceId, Long targetId, int at, int size);
	
	public Pagination getDialogsOfPeople(Long userId, int at, int size);
	
	public Pagination getUnreadDialogsOfPeople(Long userId, int at, int size);
	
	public void updatePlAsReaded(List<Long> newPlIds);
}
