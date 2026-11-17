package lt.local.emsicard;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
public class MainActivity extends Activity {
 static final String NUMBER="9440002603137578";
 @Override public void onCreate(Bundle b){super.onCreate(b);getWindow().setStatusBarColor(Color.rgb(0,64,34));getWindow().setNavigationBarColor(Color.rgb(0,64,34));getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
 LinearLayout root=new LinearLayout(this);root.setOrientation(1);root.setGravity(Gravity.CENTER);root.setPadding(18,28,18,28);root.setBackgroundColor(Color.rgb(0,65,34));
 TextView heading=new TextView(this);heading.setText("EMSI Kortelė");heading.setTextSize(30);heading.setTextColor(Color.WHITE);heading.setGravity(Gravity.CENTER);root.addView(heading);
 TextView sub=new TextView(this);sub.setText("Programa lojalumo");sub.setTextColor(0xffffd83d);sub.setTextSize(20);sub.setGravity(Gravity.CENTER);root.addView(sub);
 TextView note=new TextView(this);note.setText("LIETUVĖLĖ NEPRAŽŪS\nKOL KREPŠINIS GYVAS BUS");note.setTextColor(Color.WHITE);note.setGravity(Gravity.CENTER);note.setTextSize(17);note.setPadding(0,25,0,25);root.addView(note);
 LinearLayout panel=new LinearLayout(this);panel.setOrientation(1);panel.setPadding(12,20,12,20);panel.setBackgroundColor(Color.WHITE);root.addView(panel,new LinearLayout.LayoutParams(-1,-2));
 try { BitMatrix matrix=new MultiFormatWriter().encode(NUMBER,BarcodeFormat.CODE_128,1000,240);Bitmap bitmap=Bitmap.createBitmap(matrix.getWidth(),matrix.getHeight(),Bitmap.Config.ARGB_8888);for(int y=0;y<matrix.getHeight();y++)for(int x=0;x<matrix.getWidth();x++)bitmap.setPixel(x,y,matrix.get(x,y)?Color.BLACK:Color.WHITE);ImageView image=new ImageView(this);image.setImageBitmap(bitmap);image.setAdjustViewBounds(true);panel.addView(image,new LinearLayout.LayoutParams(-1,-2));}catch(Exception e){TextView error=new TextView(this);error.setText("Ошибка формирования штрихкода");panel.addView(error);}
 TextView number=new TextView(this);number.setText(NUMBER);number.setTextSize(23);number.setTextColor(Color.BLACK);number.setGravity(Gravity.CENTER);number.setPadding(0,12,0,0);panel.addView(number);
 Button copy=new Button(this);copy.setText("Копировать номер");copy.setOnClickListener(v->{((android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("EMSI",NUMBER));Toast.makeText(this,"Номер скопирован",Toast.LENGTH_SHORT).show();});root.addView(copy);
 TextView footer=new TextView(this);footer.setText("Работает без интернета. Неофициальная карточка.");footer.setTextColor(Color.WHITE);footer.setGravity(Gravity.CENTER);root.addView(footer);setContentView(root);
 }
}