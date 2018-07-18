package com.dwm.apr16_1_pw.follow;

import java.util.List;

public interface FollowMapper {
	public abstract int insertFollower(Follow f);
	public abstract int deleteFollower(Follow f);
	public abstract List<Follow> followOrNot(Follow f);
	public abstract List<Follow> followerCnt(Follow f);
	public abstract List<Follow> followCnt(Follow f);
}
