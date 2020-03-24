<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.util.Date"%>
<%@page import="com.itextpdf.text.DocumentException"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.cg.mobilebilling.beans.Bill"%>
<html>
<head>
<title>newGetMobileBillDetailsPage</title>
</head>
<body>
	<%-- <%!static String FILE = "E:\\billdetails1.pdf";
	static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++)
			paragraph.add(new Paragraph(" "));
	}%>
	<%
		Bill bill = (Bill) request.getAttribute("bill");
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			Paragraph preface = new Paragraph();
			addEmptyLine(preface, 1);
			preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(),
					smallBold));
			addEmptyLine(preface, 1);
			preface.add(new Paragraph("Bill Details for " + bill.getPostpaidAccount().getCustomer().getFirstName()
					+ " " + bill.getPostpaidAccount().getCustomer().getLastName() + " of the month "
					+ bill.getBillMonth(), catFont));
			addEmptyLine(preface, 1);
			document.add(preface);
			PdfPTable table = new PdfPTable(2);
			PdfPCell c1 = new PdfPCell(new Phrase("Bill ID"));
			PdfPCell c2 = new PdfPCell(new Phrase("" + bill.getBillID()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Mobile No"));
			c2 = new PdfPCell(new Phrase("" + bill.getPostpaidAccount().getMobileNo()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Bill Month"));
			c2 = new PdfPCell(new Phrase("" + bill.getBillMonth()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Local SMS Amount"));
			c2 = new PdfPCell(new Phrase("" + bill.getLocalSMSAmount()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Std SMS Amount"));
			c2 = new PdfPCell(new Phrase("" + bill.getStdSMSAmount()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Local Call Amount"));
			c2 = new PdfPCell(new Phrase("" + bill.getLocalCallAmount()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Std Call Amount"));
			c2 = new PdfPCell(new Phrase("" + bill.getStdCallAmount()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Internet Data Usage Amount"));
			c2 = new PdfPCell(new Phrase("" + bill.getInternetDataUsageAmount()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Service Tax"));
			c2 = new PdfPCell(new Phrase("" + bill.getServicesTax()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			table = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase("Total Amount"));
			c2 = new PdfPCell(new Phrase("" + bill.getTotalBillAmount()));
			table.addCell(c1);
			table.addCell(c2);
			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Process p = Runtime.getRuntime()
					.exec("rundll32 url.dll,FileProtocolHandler E:\\billdetails1.pdf");
			p.waitFor();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	%>
 --%><%-- <div align="center">
<table border="2">
<tr>
<th>Bill ID</th>
<th>Bill Month</th>
<th>localSMSAmount</th>
<th>stdSMSAmount</th>
<th>localCallAmount</th>
<th>stdCallAmount</th>
<th>internetDataUsageAmount</th>
<th>servicesTax</th>
<th>totalBillAmount</th>
</tr>
<tr>
<td><c:out value="${bill.billID}"></c:out></td>
<td><c:out value="${bill.billMonth}"></c:out></td>
<td><c:out value="${bill.localSMSAmount}"></c:out></td>
<td><c:out value="${bill.stdSMSAmount}"></c:out></td>
<td><c:out value="${bill.localCallAmount}"></c:out></td>
<td><c:out value="${bill.stdCallAmount}"></c:out></td>
<td><c:out value="${bill.internetDataUsageAmount}"></c:out></td>
<td><c:out value="${bill.servicesTax}"></c:out></td>
<td><c:out value="${bill.totalBillAmount}"></c:out></td>
</tr>
</table>
</div> --%>
</body>
</html>