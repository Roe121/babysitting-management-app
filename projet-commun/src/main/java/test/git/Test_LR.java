package test.git;

import java.time.LocalDate;
import java.time.LocalTime;

public class Test_LR {

	public static void main(String[] args) {
		
		System.out.println( "Start :) " );

		System.out.printf( "%nBonjour. " );
		System.out.printf("Nous sommes le %1$tA %1$te %1$tB %1$tY. ", LocalDate.now() );
		System.out.printf("Il est %1$tHh%1$tM.%n%n", LocalTime.now() );
		
		System.out.println( "Stop :)"  );

	}

}
