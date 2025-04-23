package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		/*
		 * 数当てゲーム擬似コード(手続き型)
		 * 
		 * タイトル・ルール表示
		 * Cpu 答えの生成 3つ
		 * プレイヤー入力 3つ
		 * プレイヤー入力値チェック false:エラーを出力して入力へ戻る
		 * Cpu Player照合判定
		 * 判定後結果表示
		 * 		true:クリアを出力	false:失敗を出力し入力へ戻る
		 */
		
		System.out.println("----MasterMind----");
		System.out.println("""
				相手が考えてる異なる3つの数字を予想して当ててください!
				入力した数と場所があっていたらHIT!!
				数のみがあっていたらBLOW
				あっていなかった場合はMISSとなります
				数字は1～6の整数でチャンスは3回です!
				""");
		
		//勝敗チェック用
		final boolean WIN = true;
		final boolean LOSE = false;
		boolean result = LOSE;
		
		//cpuの値設定
		ArrayList<Integer> cpu = new ArrayList<Integer>(3);
		for(int i = 0;i < 3;i++) {
			while(true) {
				int num = new java.util.Random().nextInt(6) + 1;
				if(!cpu.contains(num)) { //すでに登録されていないか確認
					cpu.add(num);
					break;
				}
			}	
		}
		//試行回数のカウント
		for(int count = 0;count < 3;count++) {
			System.out.printf("---%d回目---\n",count + 1);
			
			//プレイヤーの値入力
			List<Integer> player = new ArrayList<Integer>(3);
			for (int i = 0;i < 3;i++) {
				System.out.printf("%sつ目の値を入力してください\n",i+1);
				while(true) {
					try {
						int scan = new java.util.Scanner(System.in).nextInt();
						if (scan < 1 || 6 < scan) {
							throw new InputMismatchException("1～6の整数を入力してください");
						}
						if (player.contains(scan)) {
							throw new InputMismatchException("異なる値を入力してください");
						}
						player.add(scan);
						break;					
					} catch (NullPointerException e) {
						System.err.println("値を入力してください");
					} catch (InputMismatchException e) {
						if (e.getMessage() == null) {
							System.err.println("1～6の整数を入力してください");
						}else {
							System.err.println(e.getMessage());												
						}
					}
				}
			}
			
			//判定
			for(int i = 0;i < 3;i++) {
				System.out.print(player.get(i) + "：");
				if (cpu.get(i) == player.get(i)) {
					System.out.println("HIT!!");
				}else if (cpu.contains(player.get(i))) {
					System.out.println("BLOW");
				}else {
					System.out.println("MISS");
				}
			}
			if(cpu.equals(player)) {
				result = WIN;
				break;
			}
		}
		if (result) {
			System.out.println("WIN!!");
		}else {
			System.out.println("LOSE");
		}
	}

}
