package ru.Haber.GeoIP.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.rauschig.jarchivelib.ArchiveEntry;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.ArchiveStream;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import lombok.Getter;
import lombok.Setter;

public class ArchiveUtils {
	@Getter
	@Setter
	private static String folderPath;
	/*
	 * 
	 * [RU] Универсальный метод работы с архивами
	 * [EN] The universal method of working with archives
	 */
    public static void exratctFileList( String zipfilePath, String outdir ) throws IOException, URISyntaxException {
    	/*
    	 * 
    	 * [RU] Объект распаковки/место распаковки
    	 * [EN] Object of unpacking / place of unpacking
    	 */
        File archive = new File( zipfilePath );
        File destinationDir = new File( outdir );

        /*
         * 
         * [RU] Перебор методов разархивации
         * [EN] Enumeration of unzipping methods
         */
        Archiver archiver = null;
        if( zipfilePath.endsWith(".zip") ) {
            archiver = ArchiverFactory.createArchiver( ArchiveFormat.ZIP );
        } else if ( zipfilePath.endsWith(".tar.gz") ) {
            archiver = ArchiverFactory.createArchiver( ArchiveFormat.TAR, CompressionType.GZIP );
        }
        archiver.extract(archive, destinationDir);

        ArchiveStream stream = archiver.stream( archive );
        ArchiveEntry entry;
        System.out.println("Starting extract folder from bi-archive file.");
        while( (entry = stream.getNextEntry()) != null ) {  
            String entryName = entry.getName();
            if(entry.isDirectory()) {
            	setFolderPath(entry.getName());
            }
            System.out.println("Extract name : "+ entryName );
        }
        stream.close();
        System.out.println("Extracting bi-archive file successfully completed!");
        
        
    }
    public static void getFolderZIP(String zipfilePath) throws IOException {
        Archiver archiver = null;
        if( zipfilePath.endsWith(".zip")) {
            archiver = ArchiverFactory.createArchiver( ArchiveFormat.ZIP );
        } else if ( zipfilePath.endsWith(".tar.gz") ) {
            archiver = ArchiverFactory.createArchiver( ArchiveFormat.TAR, CompressionType.GZIP );
        }
        ArchiveStream stream = archiver.stream( new File(zipfilePath) );
        ArchiveEntry entry;
        while( (entry = stream.getNextEntry()) != null ) { 
            if(entry.isDirectory()) {
            	setFolderPath(entry.getName());
            }
            
        }
        stream.close();
    }

}
