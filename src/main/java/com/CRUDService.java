package com;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CRUDService {

	public String createCRUD(CRUD crud) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document(crud.getName()).set(crud);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public CRUD getCRUD(String documentId) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection("crud_user").document(documentId);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		CRUD crud;
		if(document.exists()) {
			crud = document.toObject(CRUD.class);
			return crud;
		}
		return null;
	}

	public String updateCRUD(CRUD crud) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document(crud.getName()).set(crud);
		
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deleteCRUD(String documentId) throws InterruptedException, ExecutionException {
		  Firestore dbFirestore = FirestoreClient.getFirestore();
	        ApiFuture<WriteResult> writeResult = dbFirestore.collection("crud_user").document(documentId).delete();
	        WriteResult result = writeResult.get();
	        return "Successfully Deleted " + result.getUpdateTime().toString();
	}

}
