package j1.Homework1.PracticalTasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Nds {
    public static void main(String[] args) {
        System.out.println("Налог на добавленную стоимость (НДС) входит в цену каждого товара. \nВ настоящее время " +
                "его ставка в России составляет 20%. \n Пользователь вводит стоимость товара с НДС (это 120%). " +
                "\nНеобходимо разделить её на две части: сумма без НДС (100%) и сам НДС (20%). \nПричем в исходных " +
                "документах (чеках, счетах) суммы округляются до копеек, а в налоговой декларации - до рублей. " +
                "\nСумма менее 50 коп. отбрасывается, а 50 коп. и более округляется до полного рубля.");

        System.out.println("\nВведите стоимость товара с НДС.");
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();

        while (!isDouble(inputValue)) {
            System.out.println("\nВведите стоимость товара с НДС.");
            inputValue = scanner.nextLine();
        }

        BigDecimal itemPriceWithNds = BigDecimal.valueOf(Double.parseDouble(inputValue));
        BigDecimal itemPrice = itemPriceWithNds.divide(BigDecimal.valueOf(1.2), 2, RoundingMode.HALF_UP);
        BigDecimal costNdsInReceipt = itemPriceWithNds.subtract(itemPrice).setScale(2, RoundingMode.HALF_UP);
        BigDecimal costNdsForTax = costNdsInReceipt.setScale(0, RoundingMode.HALF_UP);

        System.out.println("Стоимость товара с НДС (120%): " + itemPriceWithNds);
        System.out.println("Стоимость товара без НДС (100%): " + itemPrice);
        System.out.println("Стоимость НДС в чеке (20%) с копейками: " + costNdsInReceipt);
        System.out.println("Стоимость НДС для налоговой без копеек: " + costNdsForTax);
        System.out.println("-------------------------------\n");
    }

    private static boolean isDouble(String val) {
        try {
            Double.parseDouble(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
