import java.util.HashMap;

class DocumentRegistry {
    private HashMap<String, Document> documents = new HashMap<>();

    public void saveDocument(Document document) {
        if (!documents.containsKey(document.getNumber())) {
            documents.put(document.getNumber(), document);
            System.out.println("Документ " + document.getNumber() + " успешно сохранен в регистре.");
        } else {
            System.out.println("Документ с номером " + document.getNumber() + " уже существует в регистре.");
        }
    }

    public String getDocumentInfo(String documentNumber) {
        Document document = documents.get(documentNumber);
        if (document != null) {
            return document.getInfo();
        } else {
            return "Документ с номером " + documentNumber + " не найден в регистре.";
        }
    }
}

class Document {
    private String number;
    private String date;

    public Document(String number, String date) {
        this.number = number;
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getInfo() {
        return "Номер документа: " + number + "\nДата документа: " + date;
    }
}

class Contract extends Document {
    public Contract(String number, String date) {
        super(number, date);
    }
}

class SupplyContract extends Contract {
    private String typeOfGoods;
    private int quantity;

    public SupplyContract(String number, String date, String typeOfGoods, int quantity) {
        super(number, date);
        this.typeOfGoods = typeOfGoods;
        this.quantity = quantity;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nТип товаров: " + typeOfGoods + "\nКоличество товаров: " + quantity;
    }
}

class EmployeeContract extends Contract {
    private String startDate;
    private String endDate;
    private String employeeName;

    public EmployeeContract(String number, String date, String startDate, String endDate, String employeeName) {
        super(number, date);
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeName = employeeName;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nИмя сотрудника: " + employeeName + "\nДействителен с " + startDate + " по " + endDate;
    }
}

class FinancialInvoice extends Document {
    private double totalAmount;
    private String departmentCode;

    public FinancialInvoice(String number, String date, double totalAmount, String departmentCode) {
        super(number, date);
        this.totalAmount = totalAmount;
        this.departmentCode = departmentCode;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nИтоговая сумма за месяц: " + totalAmount + "\nКод департамента: " + departmentCode;
    }
}

// Пример использования
class Main {
    public static void main(String[] args) {
        DocumentRegistry registry = new DocumentRegistry();

        SupplyContract supplyContract = new SupplyContract("SC001", "2024-01-26", "Electronics", 100);
        EmployeeContract employeeContract = new EmployeeContract("EC001", "2024-01-26", "2024-01-26", "2024-12-31", "Vasiliy");
        FinancialInvoice financialInvoice = new FinancialInvoice("FI001", "2024-01-26", 50000, "IT");

        registry.saveDocument(supplyContract);
        registry.saveDocument(employeeContract);
        registry.saveDocument(financialInvoice);

        System.out.println(registry.getDocumentInfo("SC001"));
        System.out.println(registry.getDocumentInfo("EC001"));
        System.out.println(registry.getDocumentInfo("FI001"));
    }
}