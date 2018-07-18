package com.dwm.apr16_1_pw.waiter;

import java.util.List;

public interface WaiterMapper {
	public abstract int insertWaiter(Waiter w);
	public abstract int deleteWaiter(Waiter w);
	public abstract List<Waiter> followWaitOrNot(Waiter w);
	public abstract List<Waiter> countWaitForMe(Waiter w);
}
