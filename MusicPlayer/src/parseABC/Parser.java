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
	public String title;
	public List<VoiceNote> noteList = new ArrayList<>();
	public Parser(String loc) {
		parseFile(loc);
	}
	public void parseFile(String loc) {
		List<String> s = new ArrayList<>();
		/*Key Sig     Major   Minor    Mix     Dor     Phr     Lyd     Loc
        Ion     Aeo

7 sharps:   C#      A#m      G#Mix   D#Dor   E#Phr   F#Lyd   B#Loc
6 sharps:   F#      D#m      C#Mix   G#Dor   A#Phr   BLyd    E#Loc
5 sharps:   B       G#m      F#Mix   C#Dor   D#Phr   ELyd    A#Loc
4 sharps:   E       C#m      BMix    F#Dor   G#Phr   ALyd    D#Loc
3 sharps:   A       F#m      EMix    BDor    C#Phr   DLyd    G#Loc
2 sharps:   D       Bm       AMix    EDor    F#Phr   GLyd    C#Loc
1 sharp :   G       Em       DMix    ADor    BPhr    CLyd    F#Loc
0 sharps:   C       Am       GMix    DDor    EPhr    FLyd    BLoc
1 flat  :   F       Dm       CMix    GDor    APhr    BbLyd   ELoc
2 flats :   Bb      Gm       FMix    CDor    DPhr    EbLyd   ALoc
3 flats :   Eb      Cm       BbMix   FDor    GPhr    AbLyd   DLoc
4 flats :   Ab      Fm       EbMix   BbDor   CPhr    DbLyd   GLoc
5 flats :   Db      Bbm      AbMix   EbDor   FPhr    GbLyd   CLoc
6 flats :   Gb      Ebm      DbMix   AbDor   BbPhr   CbLyd   FLoc
7 flats :   Cb      Abm      GbMix   DbDor   EbPhr   FbLyd   BbLoc
		*/
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
	//	List<String> out = new ArrayList<>();
		while(i<l) {
			String st = s.get(i);
			String so;
			if(st.length()==0) {
				i++;
				continue;
			}
			if(st.startsWith("%")) {
				i++;
				continue;
			}else if(st.startsWith("K:")) {
				K = st.substring(2);
			}else if(st.startsWith("M:")) {
				M = st.substring(2);
			}else if(st.startsWith("Q:")) {
				Q = st.substring(2);
			}else if(st.startsWith("L:")) {
				L = st.substring(2);
			}else if(st.charAt(1)!=':') {
				//System.out.println(st);
				translate(st);
				//System.out.println(so);
			}
			i++;
		}
		/*
		try{
			String str = "Hello";
			BufferedWriter writer = new BufferedWriter(new FileWriter("out.mabc"));
			writer.write(str);
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}	*/
	}
	
	public double getValue(String s) {
		//System.out.println(s);
		if(s==">") return 1;
		if(s==null) return 1;
		if(s=="") return 1;
		String[] ss = s.split("\\/");
		if(ss.length==0) return 0.5;
		if(ss.length==1) return 1;
		double a = 1;
		if(ss[0].length()!=0) a = Double.valueOf(ss[0]);
		double b = 1;
		if(ss[1].length()!=0) b = Double.valueOf(ss[1].substring(0,1));
		System.out.printf("%s,%f \n", s,a/b);
		
		return a/b;
	}
    
	int count = 0;
	public void translate(String sg) {
		String[] ssg = sg.split("\\|");
		for(int i = 0; i < ssg.length; i++) {
			char[] ch = ssg[i].toCharArray();
			for(int j = 0; j < ch.length; j++) {
				if(ch[j]<='G'&&ch[j]>='A'||ch[j]<='g'&&ch[j]>='a') {
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
					while(j<ch.length&&!(ch[j]<='G'&&ch[j]>='A'||ch[j]<='g'&&ch[j]>='a'))
					{
						ssb.append(ch[j]);
						j++;
					}
					int len = (int)(getValue(ssb.toString())/*/getValue(this.L)*/);
					count += len;
					noteList.add(new VoiceNote(pitch,count,count+len));
					System.out.println(count);
					j--;
				}else if(ch[j]=='z') {
					j++;
					StringBuilder ssb = new StringBuilder();
					while(j<ch.length&&!(ch[j]<='G'&&ch[j]>='A'||ch[j]<='g'&&ch[j]>='a')&&ch[i]!='^'&&ch[i]!='z'&&ch[i]!='='&&ch[i]!='_')
					{
						ssb.append(ch[j]);
						j++;
					}
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
				base+=4;
				break;
			case "Gmaj":
				base+=4;
				break;
			case "D":
				base+=1;
				break;
			case "Dmaj":
				base+=1;
				break;
			case "A":
				base+=3;
				break;
			case "Amaj":
				base+=3;
				break;
			case "E":
				base+=4;
				break;
			case "Emaj":
				base+=4;
				break;
			case "B":
				base+=6;
				break;
			case "Bm":
				base+=6;
				break;
			case "Fmaj":
				base+=3;
				break;
			case "F":
				base+=3;
		}
		base += 48;
		return base;
	}
	/*public static void main(String[] args) {
		Parser p = new Parser("C:\\Users\\woshi\\Desktop\\Comp310\\Comp310\\MusicPlayer\\src\\main\\file.abc");
	}*/
}
/* 
 A  9,
 B  11,
 C  0,
 D  2,
 E  4,
 F  5,
 G  7,*/

























