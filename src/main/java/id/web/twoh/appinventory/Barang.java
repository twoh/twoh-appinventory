package id.web.twoh.appinventory;


public class Barang {
	
	private long id;
	private String nama_barang;
	private String merk_barang;
	private String harga_barang;
	
	public Barang()
	{
		
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nama_barang
	 */
	public String getNama_barang() {
		return nama_barang;
	}

	/**
	 * @param nama_barang the nama_barang to set
	 */
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}

	/**
	 * @return the merk_barang
	 */
	public String getMerk_barang() {
		return merk_barang;
	}

	/**
	 * @param merk_barang the merk_barang to set
	 */
	public void setMerk_barang(String merk_barang) {
		this.merk_barang = merk_barang;
	}

	/**
	 * @return the harga_barang
	 */
	public String getHarga_barang() {
		return harga_barang;
	}

	/**
	 * @param harga_barang the harga_barang to set
	 */
	public void setHarga_barang(String harga_barang) {
		this.harga_barang = harga_barang;
	}
	
	@Override
	public String toString()
	{
		return "Barang "+ nama_barang +" "+ merk_barang + " "+ harga_barang;
	}
}
