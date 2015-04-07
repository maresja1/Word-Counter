/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mares.utils.wordc.forms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.SoftReference;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.BoilerpipeContentHandler;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.jdesktop.observablecollections.ObservableCollections;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author Jan Mares
 */
public class DocumentReference {
    private final File file;
    private Integer count = null;
    // soft reference to allow garbage collection when memory needed
    private SoftReference<String> contentCache = new SoftReference<String>(null);

    public DocumentReference(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getContent(){
        String cached = contentCache.get();
        if(cached != null){
            return cached;
        }
        try {
            InputStream inputStream = TikaInputStream.get(file);
            Parser parser = new AutoDetectParser();
            final StringBuffer buffer = new StringBuffer();
            OutputStream stream = new OutputStream() {
                Character last = null;

                @Override
                public void write(int i) throws IOException {
                    char c = (char)i;
                    // skip multiple whitespaces
                    if(!(last != null && Character.isWhitespace(last) && Character.isWhitespace(c))){
                        buffer.append((char)i);                            
                        last = c;
                    }
                }
            };
            ContentHandler handler = new BoilerpipeContentHandler(new OutputStreamWriter(stream, Charset.defaultCharset()));
            Metadata metadata = new Metadata();
            parser.parse(inputStream, handler, metadata, new ParseContext());
            String content = buffer.toString();
            contentCache = new SoftReference<String>(content);
            return content;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DocumentReference.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentReference.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DocumentReference.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TikaException ex) {
            Logger.getLogger(DocumentReference.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getCount() {
        if(count==null){
            String content = getContent();
            if(content == null){
                count = 0;
            } else {
                count = content.length();
            }
        }
        return count;
    }
    
    public String getFileName(){
        return file.getName();
    }
}
