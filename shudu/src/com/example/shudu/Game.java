package com.example.shudu;

import android.R.string;
	
public class Game {
	private final String str = "360000000004230800000004200" 
														+ "070460003820000014500013020"
														+"001900000007048300000000045";
		
		private int  shudu []= new int [9*9];
		private int user[][][]= new int [9][9][];
		
		public Game(){
			shudu = fromPuzzieString(str);
			calculateAllUsedTiles();
		}
		

		private int getTitle(int x,int y){
			return shudu[y*9+x];
		}
		
		
		public String getTitleString(int x,int y){
			int v = getTitle(x,y);
			if(v == 0){
				return "";
			}
			else {
				return String.valueOf(v);
			}
		}
		
		protected int[] fromPuzzieString(String src) {
			int [] shudu1 = new int[src.length()];
			for(int i =0;i<shudu1.length;i++){
				shudu1[i] = src.charAt(i)-'0';
			}
			return shudu1;
		}
		
		public void calculateAllUsedTiles(){
			for(int x=0;x<9;x++){
				for(int y=0;y<9;y++){
					user[x][y] = calculatelUsedTiles(x, y);
				}
			}
		}
		
		public int[] getUsedTilesByCoor(int x,int y){
			return user[x][y];
		}
		

		public int [] calculatelUsedTiles(int x,int y){
			int c[] = new int [9];
			for(int i =0;i<9;i++){
				if(i == y)
					continue;
				int t = getTitle(x, i);
				if(t!=0)
					c[t-1]=t;
			}
			for(int i=0;i<9;i++){
				if(i == x)
					continue;
				int t = getTitle(i, y);
				if(t!=0)
					c[t-1]=t;
			}
			
			int startx = (x/3)*3;
			int starty = (y/3)*3;
			for(int i=startx;i<startx+3;i++){
				for(int j =starty;j<starty+3;j++){
					if(i==x && j==y)
						continue;
					int t = getTitle(i, j);
					if(t!=0)
						c[t-1]=t;
				}
			}
			return c;
		}
		
		
		
		protected boolean setTileIfValid(int x,int y,int value){
			int tiles[] = getUsedTiles(x,y);
			if(value !=0){
				for(int tile : tiles) {
					if(tile == value)
						return false;
				}
			}
			setTile(x,y,value);
			calculateAllUsedTiles();
			return true;
		}
		
		protected int[] getUsedTiles(int x,int y){
			return user[x][y];
		}
		private void setTile(int x,int y,int value){
				shudu[y*9+x]=value;
}
	
		
}
