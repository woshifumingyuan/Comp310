package parseABC;
import voice.VoiceNote;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Parser {
	public String K;
	public String M;
	public String L;
	public String Q;
	public List<String> V = new ArrayList<>();
	public int index = 0;
	public List<Integer> count = new ArrayList<>();
	public String title;
	public List<VoiceNote> noteList = new ArrayList<>();
	public Parser(String loc) {
		parseFile(loc);
	}
	public void parseFile(String loc) {
		List<String> s = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(loc));
			String fileRead; 
			while( (fileRead= br.readLine()) != null) {
				s.add(fileRead);
			}
		}catch(Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		int l = s.size();
		int i = 0;
		boolean headEnd = false;
		V.add(" ");
		count.add(0);
		while(i<l) {
			String st = s.get(i);
			if(st.length()==0) {
				i++;
				continue;
			}
			if(st.startsWith("%")) {
				i++;
				continue;
			}else if(st.startsWith("K:")) {
				K = st.substring(2);
				headEnd = true;
			}else if(st.startsWith("M:")) {
				M = st.substring(2);
			}else if(st.startsWith("Q:")) {
				Q = st.substring(2);
			}else if(st.startsWith("L:")) {
				L = st.substring(2);
			}else if(st.startsWith("V:")){
				if(!headEnd) {
					V.add(st);
					count.add(0);
				}else {
					for(int vi = 0; vi < V.size(); vi++) {
						System.out.println(V.get(vi));
						System.out.println(st);
						if(V.get(vi).startsWith(st)) index = vi;
					}
				}
			}else if(st.charAt(1)!=':') {
				translate(st);
			}
			i++;
		}
	}
	public boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	public double getValue(String s) {
		s = s.trim();
		if(s==">") return 1;
		if(s==null) return 1;
		if(s=="") return 1;
		if(s=="/") return 0.5;
		if(isNumber(s)) return Double.valueOf(s);
		String[] ss = s.split("\\/");
		if(ss.length==0) return 0.5;
		if(ss.length==1) return 1;
		double a = 1;
		if(ss[0].length()!=0) a = Double.valueOf(ss[0]);
		double b = 1;
		if(ss[1].length()!=0) 
		try {	
			b = Double.valueOf(ss[1].substring(0,1));
		}catch(Exception e) {
			b = 1;
		}System.out.printf("%s,%f \n", s,a/b);
		return a/b;
	}
	public void translate(String sg) {
		String[] ssg = sg.split("\\|");
		for(int i = 0; i < ssg.length; i++) {
			char[] ch = ssg[i].toCharArray();
			int ac = 0;
			for(int j = 0; j < ch.length; j++) {
				if(ch[j]=='=') {
				}
				else if(ch[j]=='_') {
					ac--;
				}
				else if(ch[j]=='^') {
					ac++;
				}else if(ch[j]<='G'&&ch[j]>='A'||ch[j]<='g'&&ch[j]>='a') {
					int pitch = determinePitch(ch[j]);
					j++;
					while(j<ch.length&&ch[j]=='\'') {
						pitch+=12;
						j++;
					}
					while(j<ch.length&&ch[j]==',') {
						pitch-=12;
						j++;
					}
					StringBuilder ssb = new StringBuilder();
					while(j<ch.length&&!(ch[j]<='G'&&ch[j]>='A'||ch[j]<='g'&&ch[j]>='a')
							&&ch[j]!='^'&&ch[j]!='z'&&ch[j]!='='&&ch[j]!='_')
					{
						ssb.append(ch[j]);
						j++;
					}
					//System.out.println(ssb.toString());
					int len = (int)(getValue(ssb.toString())/*/getValue(this.L)*/);
					//System.out.println(len);
					noteList.add(new VoiceNote(pitch+ac,count.get(index),count.get(index)+len));
					count.set(index,count.get(index)+len);
					ac=0;
					//System.out.println(count);
					j--;
				}else if(ch[j]=='z') {
					j++;
					StringBuilder ssb = new StringBuilder();
					while(j<ch.length&&!(ch[j]<='G'&&ch[j]>='A'||ch[j]<='g'&&ch[j]>='a')
							&&ch[j]!='^'&&ch[j]!='z'&&ch[j]!='='&&ch[j]!='_')
					{
						ssb.append(ch[j]);
						j++;
					}
					int len = (int)(getValue(ssb.toString()));
					count.set(index,count.get(index)+len);
				}
			}
		}
	}
	
	public int determinePitch(char c) {
		int base = 0;
		switch(c) {
			case 'A': base = 9;
			break;
			case 'B': base = 11;
			break;
			case 'C': base = 0;
			break;
			case 'D': base = 2;
			break;
			case 'E': base = 4;
			break;
			case 'F': base = 5;
			break;
			case 'G': base = 7;
			break;
			case 'a': base = 21;
			break;
			case 'b': base = 23;
			break;
			case 'c': base = 12;
			break;
			case 'd': base = 14;
			break;
			case 'e': base = 16;
			break;
			case 'f': base = 17;
			break;
			case 'g': base = 19;
			break;
		}
		switch(this.K) {
			case "C":
				break;
			case "G":
				base+=7;
				break;
			case "Gmaj":
				base+=7;
				break;
			case "D":
				base+=2;
				break;
			case "Dmaj":
				base+=2;
				break;
			case "A":
				base+=9;
				break;
			case "Amaj":
				base+=9;
				break;
			case "E":
				base+=4;
				break;
			case "Emaj":
				base+=4;
				break;
			case "B":
				base+=11;
				break;
			case "Bm":
				base+=11;
				break;
			case "Fmaj":
				base+=5;
				break;
			case "F":
				base+=5;
		}
		base += 60;
		return base;
	}
}