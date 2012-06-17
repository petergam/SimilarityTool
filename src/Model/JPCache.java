package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;

import Utilities.GUILog;

/**
 * The Class JPCache.
 * Cache a Objects in memory and on disk. Uses key/value
 */
public enum JPCache {
	
	/** The Shared cache instance. */
	SharedCache;
	
	/** The cache hashmap. */
	private ConcurrentHashMap<Object, Object> cacheHashmap;
	
	/** The cache name. */
	private String cacheName;
	
	/** The Constant sDefaultCacheName. */
	public static final String sDefaultCacheName = "DefaultCache";
	

	/** The is dirty. */
	private boolean isDirty = false;

	/**
	 * Load cache.
	 *
	 * @param cacheName the cache name
	 */
	public void loadCache(String cacheName) {
		this.cacheName = cacheName;
		
		try {
			URL url = this.getClass().getResource("/Cache/");

			FileInputStream fis = new FileInputStream(url.getPath() + cacheName
					+ ".jp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			cacheHashmap = (ConcurrentHashMap<Object, Object>) ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			cacheHashmap = new ConcurrentHashMap<Object, Object>();
		} catch (IOException e) {
			System.out.println("Cache load IOexception");
		} catch (ClassNotFoundException e) {
			System.out.println("Cache classNotFound error");
		}
	}

	/**
	 * Sets the cached value.
	 *
	 * @param value the value
	 * @param key the key
	 */
	public void setCachedValue(Object value, Object key) {
		isDirty = true;
		cacheHashmap.put(key, value);
	}
	
	/**
	 * Gets the cached value.
	 *
	 * @param key the key
	 * @return the cached value
	 */
	public Object getCachedValue(Object key) {
		return cacheHashmap.get(key.toString());
	}
	
	/**
	 * Synchronize.
	 * Saves the cache to disk.
	 */
	public synchronized void synchronize() {
		if (isDirty) {
			isDirty = false;
			try {
				URL url = this.getClass().getResource("/Cache/");
				FileOutputStream fos = new FileOutputStream(url.getPath()+ cacheName + ".jp");
				ObjectOutputStream oos = new ObjectOutputStream(fos); 
				oos.writeObject(cacheHashmap); 
				oos.flush(); 
				oos.close(); 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	
	/**
	 * Clear the cache.
	 * Clears both memory and disk cache
	 */
	public synchronized void clear() {
		GUILog.nLog("Clearing cache");
		cacheHashmap.clear();
		try {
			URL url = this.getClass().getResource("/Cache/");
			FileUtils.cleanDirectory(new File(url.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the cache name.
	 *
	 * @return the cache name
	 */
	public String getCacheName() {
		return cacheName;
	}

	/**
	 * Sets the cache name.
	 *
	 * @param cacheName the new cache name
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
}
