package org.warren.sca.rsc.common.postmaninfo;

public interface PostmanClockService {

    void clockIn(int postmanId);

    void clockOut(int postmanId);

}
