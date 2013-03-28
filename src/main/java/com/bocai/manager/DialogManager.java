package com.bocai.manager;

import java.util.List;

import com.bocai.common.page.Pagination;
import com.bocai.dao.domain.Dialog;

public interface DialogManager extends BaseManager<Dialog> {
	
	public Dialog getDialog(Long sourceId, Long targetId);

	public Pagination getDialogDetail(Long sourceId, Long targetId, int at, int size);

	public Pagination getDialogsOfPeople(Long userId, int at, int size);
	
	public void sendPrivateLetter(Long sourceId, Long targetId, String content, int way);
	
	public Long newPrivateLetterCount(Long userId);
	
	public Pagination getUnreadDialogsOfPeople(Long userId, int at, int size);
	
	public void updatePlAsReaded(List<Long> newPlIds);
}
