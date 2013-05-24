package com.asccode.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.asccode.model.Loja;

public class LojaDao extends SQLiteOpenHelper {
	
	public static final String NOME_TABELA = "lojas";
	public static final String []COLUNAS = {"id", "nome", "key"};
	public static final int VERSION = 7;
	
	private Context context;
	
	public LojaDao(Context context){
		
		super(context, LojaDao.NOME_TABELA, null, LojaDao.VERSION );
		
		this.context = context;
		
	}
	
	public void inserir(Loja loja){
		
		ContentValues cv = new ContentValues();
		cv.put(LojaDao.COLUNAS[1], loja.getNome() );
		cv.put(LojaDao.COLUNAS[2], loja.getKey() );

		getWritableDatabase().insert(LojaDao.NOME_TABELA, null, cv); 
						
	}
	
	
	public void atualizar(Loja loja){
		
		ContentValues cv = new ContentValues();
		cv.put(LojaDao.COLUNAS[1], loja.getNome() );
		cv.put(LojaDao.COLUNAS[2], loja.getKey() );

		getWritableDatabase().update(LojaDao.NOME_TABELA, cv, "id=?", new String[]{String.valueOf(loja.getId())});
		
	}
	

	public void excluir(Loja loja) {
		
		getWritableDatabase().delete(LojaDao.NOME_TABELA, "id=?", new String[]{String.valueOf(loja.getId())});

		
	}

	
	public List<Loja> pegaTodasAsLojas(){
		
		Cursor cursor = getWritableDatabase().query(LojaDao.NOME_TABELA, LojaDao.COLUNAS, null, null, null, null, null);
		
		List<Loja> lojas = new ArrayList<Loja>();
		
		try{

			while(cursor.moveToNext()){
				
				Loja lojaTemp = new Loja();
				lojaTemp.setId( cursor.getInt(0) );
				lojaTemp.setNome( cursor.getString(1) );
				lojaTemp.setKey( cursor.getString(2) );

				lojas.add(lojaTemp);
				
			}
			
		}finally{

			cursor.close();	
			getWritableDatabase().close();
			
		}
				
		return lojas;
		
	}
	
	public Loja pegaLojaPeloId( int id ){
		
		Cursor cursor = getWritableDatabase().query(LojaDao.NOME_TABELA, LojaDao.COLUNAS, "id=?", new String[]{String.valueOf(id)}, null, null, null);
		cursor.moveToNext();
		
		int total = cursor.getCount();
		
		Loja lojaResgatada = null;
		
		if(total == 1){
			
			lojaResgatada = new Loja();
			lojaResgatada.setId( cursor.getInt(0) );
			lojaResgatada.setNome( cursor.getString(1) );
			lojaResgatada.setKey( cursor.getString(2) );

		}else{
			
			throw new RuntimeException("A identifica��o da loja n�o existe no nosso banco de dados");
			
		}
		
		cursor.close();
		getWritableDatabase().close();

		
		return lojaResgatada;
		
	}
	

	public Loja pegaLojaPelaKey(String keyLoja) {
		
		Cursor cursor = getWritableDatabase().query(LojaDao.NOME_TABELA, LojaDao.COLUNAS, "key=?", new String[]{keyLoja}, null, null, null);
		cursor.moveToNext();
		
		int total = cursor.getCount();
		
		Loja lojaResgatada = null;
		
		if(total == 1){
			
			lojaResgatada = new Loja();
			lojaResgatada.setId( cursor.getInt(0) );
			lojaResgatada.setNome( cursor.getString(1) );
			lojaResgatada.setKey( cursor.getString(2) );

		}else{
			
			throw new RuntimeException("A key da loja n�o existe no nosso banco de dados");
			
		}
		
		cursor.close();
		getWritableDatabase().close();

		
		return lojaResgatada;
	}
	
	public void onCreate( SQLiteDatabase db){
		
		String sqlCriarTable = "CREATE TABLE IF NOT EXISTS "+LojaDao.NOME_TABELA+"(id INTEGER PRIMARY KEY, nome TEXT UNIQUE NOT NULL, key TEXT UNIQUE NOT NULL);";
		db.execSQL(sqlCriarTable);
		
	}
	
	@Override
	public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ){
	
		db.execSQL("DROP TABLE IF EXISTS "+LojaDao.NOME_TABELA+";");
		this.onCreate(db);
		
	}
	
}
