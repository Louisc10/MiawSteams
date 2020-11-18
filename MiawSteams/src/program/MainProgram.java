package program;

import controller.BasicController;
import model.User;

public class MainProgram {
	
	public static boolean isRunning = true;
	public static final String LOGO = "\n  o          o     o                                            o__ __o      o                                                           \n <|\\        /|>  _<|>_                                         /v     v\\    <|>                                                          \n / \\\\o    o// \\                                               />       <\\   < >                                                          \n \\o/ v\\  /v \\o/    o       o__ __o/   o              o       _\\o____         |        o__  __o      o__ __o/  \\o__ __o__ __o       __o__ \n  |   <\\/>   |    <|>     /v     |   <|>            <|>           \\_\\__o__   o__/_   /v      |>    /v     |    |     |     |>     />  \\  \n / \\        / \\   / \\    />     / \\  < >            < >                 \\    |      />      //    />     / \\  / \\   / \\   / \\     \\o     \n \\o/        \\o/   \\o/    \\      \\o/   \\o    o/\\o    o/        \\         /    |      \\o    o/      \\      \\o/  \\o/   \\o/   \\o/      v\\    \n  |          |     |      o      |     v\\  /v  v\\  /v          o       o     o       v\\  /v __o    o      |    |     |     |        <\\   \n / \\        / \\   / \\     <\\__  / \\     <\\/>    <\\/>           <\\__ __/>     <\\__     <\\/> __/>    <\\__  / \\  / \\   / \\   / \\  _\\o__</   \n                                                                                                                                         \n                                                                                                                                         \n                                                                                                                                        \n";
	public static User currentUser = null;
	public static int currentSpeed = 20000;
	public static int pagination = 6;
	
	public static void main(String[] args) {
		BasicController.getInstance().doStart(); 			
	}
	
}
