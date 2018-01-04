package com.medical.service;

import com.medical.dao.MedicalPointDao;
import com.medical.domain.Coordinates;
import com.medical.domain.MedicalPoint;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RunWith(MockitoJUnitRunner.class)
public class FindMedicalPointServiceTest {

    @Mock
    private MedicalPointDao medicalPointDao;

    @InjectMocks
    private FindMedicalPointServiceImpl findMedicalPointService;

    private double latitude;
    private double longitude;
    private String illnessName;
    private String specialtyName;
    private String cityName;
    private String provinceName;

    @Before
    public void setupMock()
    {
        MockitoAnnotations.initMocks(this);
        latitude = 50.0;
        longitude = 20.0;
        illnessName = "illness";
        specialtyName = "speciality";
        cityName = "city";
        provinceName = "province";
    }

    @Test
    public void testMockCreation()
    {
        assertNotNull(medicalPointDao);
    }
    @Test
    public void testFindingByIllnessAndCity()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 3);

        System.out.println("Stubbing findWithIllnessAndCity(illnessName, cityName) to return not empty list");
        when(medicalPointDao.findWithIllnessAndCity(illnessName, cityName)).thenReturn(filledList);
        System.out.println("Stubbing findWithIllnessAndProvince(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndProvince(illnessName, provinceName)).thenReturn(emptyList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointByIllness(latitude, longitude," +
                "illnessName, cityName, provinceName)");
        findMedicalPointService.getNearestMedicalPointByIllness(latitude, longitude,
              illnessName, cityName, provinceName);

        System.out.println("Verifying findWithIllnessAndCity(illnessName, cityName) is called at least once");
        verify(medicalPointDao, atLeastOnce()).findWithIllnessAndCity(illnessName, cityName);
        System.out.println("Verifying findWithIllnessAndProvince(illnessName, cityName) is never called");
        verify(medicalPointDao, never()).findWithIllnessAndProvince(illnessName,provinceName);
    }

    @Test
    public void testFindingByIllnessAndProvince()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 3);

        System.out.println("Stubbing findWithIllnessAndCity(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndCity(illnessName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithIllnessProvince(illnessName, cityName) to return not empty list");
        when(medicalPointDao.findWithIllnessAndProvince(illnessName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointByIllness(latitude, longitude," +
                "illnessName, cityName, provinceName)");
        findMedicalPointService.getNearestMedicalPointByIllness(latitude, longitude,
                illnessName, cityName, provinceName);

        System.out.println("Verifying findWithIllnessAndCity(illnessName, cityName) is called at least once");
        verify(medicalPointDao, atLeastOnce()).findWithIllnessAndCity(illnessName, cityName);
        System.out.println("Verifying findWithIllnessAndProvince(illnessName, cityName) is called at least once");
        verify(medicalPointDao, atLeastOnce()).findWithIllnessAndProvince(illnessName,provinceName);

        System.out.println("Verifying order of method calls on ProductDao");
        InOrder order = inOrder(medicalPointDao);
        order.verify(medicalPointDao).findWithIllnessAndCity(illnessName, cityName);
        order.verify(medicalPointDao).findWithIllnessAndProvince(illnessName, provinceName);

    }

    @Test
    public void findingWithIllnessEmptyLists()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        System.out.println("Stubbing findWithIllnessAndCity(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndCity(illnessName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithIllnessAndProvince(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndProvince(illnessName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointByIllness(latitude, longitude," +
                "illnessName, cityName, provinceName)");
        MedicalPoint medicalPoint = findMedicalPointService.getNearestMedicalPointByIllness(latitude, longitude,
                illnessName, cityName, provinceName);

       System.out.println("Checking if findMedicalPoint returning null");
        assertEquals(null, medicalPoint);
    }

    @Test
    public void findingWithIllnessOneMedicalPoint()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 1);

        System.out.println("Stubbing findWithIllnessAndCity(illnessName, cityName) to return list with one element");
        when(medicalPointDao.findWithIllnessAndCity(illnessName, cityName)).thenReturn(filledList);
        System.out.println("Stubbing findWithIllnessAndProvince(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndProvince(illnessName, provinceName)).thenReturn(emptyList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointByIllness(latitude, longitude," +
                "illnessName, cityName, provinceName)");
        MedicalPoint medicalPoint = findMedicalPointService.getNearestMedicalPointByIllness(latitude, longitude,
                illnessName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(0).getName(), medicalPoint.getName());

        System.out.println("then");

        System.out.println("Stubbing findWithIllnessAndCity(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndCity(illnessName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithIllnessAndProvince(illnessName, cityName) to return list with one element");
        when(medicalPointDao.findWithIllnessAndProvince(illnessName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointByIllness(latitude, longitude," +
                "illnessName, cityName, provinceName)");
        MedicalPoint medicalPoint1 = findMedicalPointService.getNearestMedicalPointByIllness(latitude, longitude,
                illnessName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(0).getName(), medicalPoint1.getName());

    }

    @Test
    public void findingWithIllnessManyMedicalPoints()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 10);

        int randomElement = (int)(Math.random() * 10);
        longitude = filledList.get(randomElement).getCoordinates().getLongitude();
        latitude = filledList.get(randomElement).getCoordinates().getLatitude();


        System.out.println("Stubbing findWithIllnessAndCity(illnessName, cityName) to return filled list");
        when(medicalPointDao.findWithIllnessAndCity(illnessName, cityName)).thenReturn(filledList);
        System.out.println("Stubbing findWithIllnessAndProvince(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndProvince(illnessName, provinceName)).thenReturn(emptyList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointByIllness(latitude, longitude," +
                "illnessName, cityName, provinceName)");
        MedicalPoint medicalPoint = findMedicalPointService.getNearestMedicalPointByIllness(latitude, longitude,
                illnessName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(randomElement).getName(), medicalPoint.getName());

        System.out.println("then");

        System.out.println("Stubbing findWithIllnessAndCity(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithIllnessAndCity(illnessName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithIllnessAndProvince(illnessName, cityName) to return filled list");
        when(medicalPointDao.findWithIllnessAndProvince(illnessName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointByIllness(latitude, longitude," +
                "illnessName, cityName, provinceName)");
        MedicalPoint medicalPoint1 = findMedicalPointService.getNearestMedicalPointByIllness(latitude, longitude,
                illnessName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(randomElement).getName(), medicalPoint1.getName());
    }

    @Test
    public void testFindingBySpecialtyAndCity()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 3);

        System.out.println("Stubbing findWithSpecialtyAndCity(specialtyName, cityName) to return not empty list");
        when(medicalPointDao.findWithSpecialtyAndCity(specialtyName, cityName)).thenReturn(filledList);
        System.out.println("Stubbing findWithSpecialtyAndProvince(specialtyName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndProvince(specialtyName, provinceName)).thenReturn(emptyList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointBySpecialty(latitude, longitude," +
                "specialtyName, cityName, provinceName)");
        findMedicalPointService.getNearestMedicalPointBySpecialty(latitude, longitude,
                specialtyName, cityName, provinceName);

        System.out.println("Verifying findWithSpecialtyAndCity(specialtyName, cityName) is called at least once");
        verify(medicalPointDao, atLeastOnce()).findWithSpecialtyAndCity(specialtyName, cityName);
        System.out.println("Verifying findWithSpecialtyAndProvince(specialtyName, cityName) is never called");
        verify(medicalPointDao, never()).findWithSpecialtyAndProvince(specialtyName,provinceName);
    }

    @Test
    public void testFindingBySpecialtyAndProvince()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 3);

        System.out.println("Stubbing findWithSpecialtyAndCity(specialtyName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndCity(specialtyName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithSpecialtyAndProvince(specialtyName, cityName) to return filled list");
        when(medicalPointDao.findWithSpecialtyAndProvince(specialtyName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointBySpecialty(latitude, longitude," +
                "specialtyName, cityName, provinceName)");
        findMedicalPointService.getNearestMedicalPointBySpecialty(latitude, longitude,
                specialtyName, cityName, provinceName);

        System.out.println("Verifying findWithSpecialtyAndCity(specialtyName, cityName) is called at least once");
        verify(medicalPointDao, atLeastOnce()).findWithSpecialtyAndCity(specialtyName, cityName);
        System.out.println("Verifying findWithSpecialtyAndProvince(specialtyName, cityName) is called at least once");
        verify(medicalPointDao, atLeastOnce()).findWithSpecialtyAndProvince(specialtyName,provinceName);
    }


    @Test
    public void findingWithSpecEmptyLists()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        System.out.println("Stubbing findWithSpecialtyAndCity(specialtyName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndCity(specialtyName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithSpecialtyAndProvince(specialtyName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndProvince(specialtyName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointBySpecialty(latitude, longitude," +
                "specialtyName, cityName, provinceName)");
        MedicalPoint medicalPoint = findMedicalPointService.getNearestMedicalPointBySpecialty(latitude, longitude,
                specialtyName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning null");
        assertEquals(null, medicalPoint);
    }

    @Test
    public void findingWithSpecOneMedicalPoint()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 1);

        System.out.println("Stubbing findWithSpecialtyAndCity(specialtyName, cityName) to return list with one element");
        when(medicalPointDao.findWithSpecialtyAndCity(specialtyName, cityName)).thenReturn(filledList);
        System.out.println("Stubbing findWithSpecialtyAndProvince(specialtyName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndProvince(specialtyName, provinceName)).thenReturn(emptyList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointBySpecialty(latitude, longitude," +
                "specialtyName, cityName, provinceName)");
        MedicalPoint medicalPoint = findMedicalPointService.getNearestMedicalPointBySpecialty(latitude, longitude,
                specialtyName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(0).getName(), medicalPoint.getName());

        System.out.println("then");

        System.out.println("Stubbing findWithSpecialtyAndCity(specialtyName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndCity(specialtyName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithSpecialtyAndProvince(specialtyName, cityName) to return list with one element");
        when(medicalPointDao.findWithSpecialtyAndProvince(specialtyName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointBySpecialty(latitude, longitude," +
                "specialtyName, cityName, provinceName)");
        MedicalPoint medicalPoint1 = findMedicalPointService.getNearestMedicalPointBySpecialty(latitude, longitude,
                specialtyName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(0).getName(), medicalPoint1.getName());

    }

    @Test
    public void findingWithSpecManyMedicalPoints()
    {
        List<MedicalPoint> filledList = new ArrayList<>();
        List<MedicalPoint> emptyList = new ArrayList<>();

        initList(filledList, 10);

        int randomElement = (int)(Math.random() * 10);
        longitude = filledList.get(randomElement).getCoordinates().getLongitude();
        latitude = filledList.get(randomElement).getCoordinates().getLatitude();


        System.out.println("Stubbing findWithSpecialtyAndCity(specialtyName, cityName) to return filled list");
        when(medicalPointDao.findWithSpecialtyAndCity(specialtyName, cityName)).thenReturn(filledList);
        System.out.println("Stubbing findWithSpecialtyAndProvince(illnessName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndProvince(specialtyName, provinceName)).thenReturn(emptyList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointBySpecialty(latitude, longitude," +
                "SpecialtyName, cityName, provinceName)");
        MedicalPoint medicalPoint = findMedicalPointService.getNearestMedicalPointBySpecialty(latitude, longitude,
                specialtyName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(randomElement).getName(), medicalPoint.getName());

        System.out.println("then");

        System.out.println("Stubbing findWithSpecialtyAndCity(specialtyName, cityName) to return empty list");
        when(medicalPointDao.findWithSpecialtyAndCity(specialtyName, cityName)).thenReturn(emptyList);
        System.out.println("Stubbing findWithSpecialtyAndProvince(specialtyName, cityName) to return filled list");
        when(medicalPointDao.findWithSpecialtyAndProvince(specialtyName, provinceName)).thenReturn(filledList);

        System.out.println("Calling FindMedicalPointService.etNearestMedicalPointBySpecialty(latitude, longitude," +
                "specialtyName, cityName, provinceName)");
        MedicalPoint medicalPoint1 = findMedicalPointService.getNearestMedicalPointBySpecialty(latitude, longitude,
                specialtyName, cityName, provinceName);

        System.out.println("Checking if findMedicalPoint returning correct mp");
        assertEquals(filledList.get(randomElement).getName(), medicalPoint1.getName());
    }


    private void initList(List<MedicalPoint> list, int numberOfElements)
    {
        for(int i=0; i < numberOfElements; ++i)
        {
            MedicalPoint medicalPoint = new MedicalPoint();
            Coordinates coordinates = new Coordinates();
            Random r = new Random();
            double lat = 40 + (60 - 40) * r.nextDouble();
            double lon = 15 + (25 - 15) * r.nextDouble();
            coordinates.setLatitude(lat);
            coordinates.setLongitude(lon);
            medicalPoint.setCoordinates(coordinates);
            medicalPoint.setName("Hospital" + Integer.toString(i+1));
            list.add(medicalPoint);
        }

    }
}