package org.warren.sca.rsc.common.postmaninfo;

import org.warren.sca.rsc.common.postmaninfo.dto.PostmanWorkDTO;

import java.util.List;

public interface PostmanWorkService {

    void addPickUpTask(long orderId, int postmanId);

    void addDistributionTask(long orderId, int postmanId);

    void finishPickUpTask(int postmanId, long orderId);

    void finishDistributionTask(int postmanId, long orderId);

    List<PostmanWorkDTO> listAllTask(int postmanId, int offset, int count);

    List<PostmanWorkDTO> listAllUnDoneTask(int postmanId);

}
