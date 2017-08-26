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
				so = translate(st);
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
		System.out.println(s);
		if(s==null) return 1;s.replace('|', ' ');
		if(s=="") return 1;
		String[] ss = s.split("\\/");
		if(ss.length==0) return 0.5;
		if(ss.length==1) return Double.valueOf(s);
		double a = Double.valueOf(ss[0]);
		double b = Double.valueOf(ss[1]);
		return a/b;
	}
    
	int count = 0;
	public String translate(String sg) {
		char[] ar = sg.toCharArray();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < ar.length) {
			int sharp = 0;String l = "";
			if(ar[i]>='a'&&ar[i]<='g'||ar[i]>='A'&&ar[i]<='G') {
				char n = ar[i];
				i++;
				while(i<ar.length&&!(ar[i]>='a'&&ar[i]<='g'||ar[i]>='A'&&ar[i]<='G')) {
					if(ar[i]=='\''){
						sharp++;
					}else if(ar[i] == ','){
						sharp--;
					}else {
						l = l+""+ar[i];
					}
					i++;
				}
				int compute = (int)(getValue(l)*getValue(L)*16);
				count += compute;
				System.out.println(count);
				VoiceNote vn = new VoiceNote(n-'A'+60+sharp,count, compute);
				noteList.add(vn);
				i++;
			}else i++;
		}
		return sb.toString();
	}
	/*public static void main(String[] args) {
		Parser p = new Parser("C:\\Users\\woshi\\Desktop\\Comp310\\Comp310\\MusicPlayer\\src\\main\\file.abc");
	}*/
}
