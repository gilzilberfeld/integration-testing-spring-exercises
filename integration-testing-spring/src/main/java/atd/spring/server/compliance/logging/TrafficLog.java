package atd.spring.server.compliance.logging;

import atd.spring.server.bills.Bill;

public class TrafficLog implements BillLog {

	StringBuilder log = new StringBuilder();

	@Override
	public void log(Bill b) {
		log.append(">");
		log.append(b.toString());
		log.append("\n");
	}

	@Override
	public String getAllAsString() {
		return log.toString();
	}

}
