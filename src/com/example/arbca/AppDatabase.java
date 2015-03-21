package com.example.arbca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {
	
	final static String db_name = "rbca_db";
	final static int db_version = 1;

	public AppDatabase(Context context) {
		super(context, db_name, null, db_version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// When database is created, create and insert RBSL table
		db.execSQL("CREATE TABLE rbsls (_id INTEGER PRIMARY KEY, media TEXT, receptor TEXT, gw_use TEXT, soil_type TEXT, benzene NUMERIC, toluene NUMERIC, ethylbenzene NUMERIC, xylenes NUMERIC, gasoline NUMERIC, fuel NUMERIC, lube NUMERIC);");
		db.execSQL("INSERT INTO rbsls VALUES(1,'Soil','Agricultural','Potable','Coarse-grained',0.042,0.35,0.065,8.8,74,270,1100);");
		db.execSQL("INSERT INTO rbsls VALUES(2,'Soil','Agricultural','Potable','Fine-grained',0.094,0.74,0.13,22,1900,4700,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(3,'Soil','Agricultural','Non-Potable','Coarse-grained',0.099,77,30,8.8,74,270,1100);");
		db.execSQL("INSERT INTO rbsls VALUES(4,'Soil','Agricultural','Non-Potable','Fine-grained',2.3,10000,9300,210,2100,8600,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(5,'Soil','Residential','Potable','Coarse-grained',0.042,0.35,0.065,8.8,74,270,1100);");
		db.execSQL("INSERT INTO rbsls VALUES(6,'Soil','Residential','Potable','Fine-grained',0.094,0.74,0.13,22,1900,4700,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(7,'Soil','Residential','Non-Potable','Coarse-grained',0.099,77,30,8.8,74,270,1100);");
		db.execSQL("INSERT INTO rbsls VALUES(8,'Soil','Residential','Non-Potable','Fine-grained',2.3,10000,9300,210,2100,8600,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(9,'Soil','Commercial','Potable','Coarse-grained',0.042,0.35,0.065,11,870,1800,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(10,'Soil','Commercial','Potable','Fine-grained',0.094,0.74,0.13,22,1900,4700,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(11,'Soil','Commercial','Non-Potable','Coarse-grained',2.5,10000,10000,110,870,4000,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(12,'Soil','Commercial','Non-Potable','Fine-grained',33,10000,10000,10000,10000,10000,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(13,'Soil','Industrial','Potable','Coarse-grained',0.042,0.35,0.065,11,870,1800,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(14,'Soil','Industrial','Potable','Fine-grained',0.094,0.74,0.13,22,1900,4700,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(15,'Soil','Industrial','Non-Potable','Coarse-grained',2.5,10000,10000,110,870,4000,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(16,'Soil','Industrial','Non-Potable','Fine-grained',33,10000,10000,10000,10000,10000,10000);");
		db.execSQL("INSERT INTO rbsls VALUES(17,'Groundwater','Agricultural','Potable','Coarse-grained',0.005,0.024,0.0024,0.3,4.4,3.2,7.8);");
		db.execSQL("INSERT INTO rbsls VALUES(18,'Groundwater','Agricultural','Potable','Fine-grained',0.005,0.024,0.0024,0.3,4.4,3.2,7.8);");
		db.execSQL("INSERT INTO rbsls VALUES(19,'Groundwater','Agricultural','Non-Potable','Coarse-grained',2.6,20,20,20,20,20,20);");
		db.execSQL("INSERT INTO rbsls VALUES(20,'Groundwater','Agricultural','Non-Potable','Fine-grained',13,20,20,20,20,20,20);");
		db.execSQL("INSERT INTO rbsls VALUES(21,'Groundwater','Residential','Potable','Coarse-grained',0.005,0.024,0.0024,0.3,4.4,3.2,7.8);");
		db.execSQL("INSERT INTO rbsls VALUES(22,'Groundwater','Residential','Potable','Fine-grained',0.005,0.024,0.0024,0.3,4.4,3.2,7.8);");
		db.execSQL("INSERT INTO rbsls VALUES(23,'Groundwater','Residential','Non-Potable','Coarse-grained',2.6,20,20,20,20,20,20);");
		db.execSQL("INSERT INTO rbsls VALUES(24,'Groundwater','Residential','Non-Potable','Fine-grained',13,20,20,20,20,20,20);");
		db.execSQL("INSERT INTO rbsls VALUES(25,'Groundwater','Commercial','Potable','Coarse-grained',0.005,0.024,0.0024,0.3,4.4,3.2,7.8);");
		db.execSQL("INSERT INTO rbsls VALUES(26,'Groundwater','Commercial','Potable','Fine-grained',0.005,0.024,0.0024,0.3,4.4,3.2,7.8);");
		db.execSQL("INSERT INTO rbsls VALUES(27,'Groundwater','Commercial','Non-Potable','Coarse-grained',20,20,20,20,20,20,20);");
		db.execSQL("INSERT INTO rbsls VALUES(28,'Groundwater','Commercial','Non-Potable','Fine-grained',20,20,20,20,20,20,20);");
		db.execSQL("INSERT INTO rbsls VALUES(29,'Groundwater','Industrial','Potable','Coarse-grained',0.005,0.024,0.0024,0.3,4.4,3.2,7.8);");
		db.execSQL("INSERT INTO rbsls VALUES(30,'Groundwater','Industrial','Potable','Fine-grained',0.005,0.024,0.0024,0.3,4.4,3.2,8);");
		db.execSQL("INSERT INTO rbsls VALUES(31,'Groundwater','Industrial','Non-Potable','Coarse-grained',20,20,20,20,20,20,20);");
		db.execSQL("INSERT INTO rbsls VALUES(32,'Groundwater','Industrial','Non-Potable','Fine-grained',20,20,20,20,20,20,20);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
