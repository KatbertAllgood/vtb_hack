package com.example.vtb_hackathon.ui.screen.map

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vtb_hackathon.R
import com.example.vtb_hackathon.mappers.toPoint
import com.example.vtb_hackathon.ui.screen.map.bottomsheet.DepartmentInfo
import com.example.vtb_hackathon.ui.screen.map.bottomsheet.MapNavigationBottom
import com.example.vtb_hackathon.ui.screen.map.item.DepartmentItem
import com.example.vtb_hackathon.ui.screen.map.item.NavigatorItem
import com.example.vtb_hackathon.ui.theme.VTBTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.Route
import com.yandex.mapkit.transport.masstransit.Session.RouteListener
import com.yandex.mapkit.transport.masstransit.TimeOptions
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider


class MapScreenManipulator(
    private val context: Context,
    private val activityContext: Context
) : UserLocationObjectListener, CameraListener {

    private val TAG = MapScreenManipulator::class.simpleName

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var userLocationLayer: UserLocationLayer

    private lateinit var currentPosition: Point
    private var currentZoom: Float = 0F
    private var count = 0

    private lateinit var mapView: MapView

    override fun onObjectRemoved(p0: UserLocationView) {

    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {

    }

    override fun onCameraPositionChanged(
        p0: Map,
        p1: CameraPosition,
        p2: CameraUpdateReason,
        p3: Boolean
    ) {

    }

    override fun onObjectAdded(userLocationView: UserLocationView) {

        userLocationLayer.setAnchor(
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.5).toFloat()),
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.83).toFloat()),
        )

        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                context,
                R.drawable.ic_user_on_map_arrow
            )
        )

        val picIcon = userLocationView.pin.useCompositeIcon()

        picIcon.setIcon(
            "icon",
            ImageProvider.fromResource(
                context,
                R.drawable.ic_user_on_map
            ),
            IconStyle().setAnchor(PointF(0.5F, 0.5F))
                .setRotationType(RotationType.ROTATE).setZIndex(1F).setScale(0.5F)
        )

    }

    private fun moveToLocation(
        mapView: MapView,
        userPosition: Point
    ) {
        currentZoom = 15.0F
        currentPosition = userPosition
        mapView.map.move(
            CameraPosition(currentPosition, currentZoom, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 2F),
            null
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MapScreen(
        viewModel: MapVM = viewModel()
    ) {

        val scaffoldStateForOffices = rememberBottomSheetScaffoldState()
//        var scaffoldSheetPeekHeight by rememberSaveable {
//            mutableStateOf(50.dp)
//        }

        val modalStateForNavigation = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var isModalSheetForNavigationOpen by rememberSaveable {
            mutableStateOf(false)
        }

        val itemDepartmentColumnState = rememberScrollState()

        val filterModalSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var isFilterModalSheetOpen by rememberSaveable {
            mutableStateOf(false)
        }

        val departmentInfoModalSheetState =
            rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var isDepartmentInfoModalSheetOpen by rememberSaveable {
            mutableStateOf(false)
        }

        var isNavigationStarted by rememberSaveable {
            mutableStateOf(false)
        }

        val scrollServicesState = rememberScrollState()

        val servicesList = viewModel.getServicesLiveData.observeAsState().value

        val officeList = viewModel.getCurrentOfficesList.observeAsState().value

        val chosenOffice = viewModel.getChosenOffice.observeAsState().value

        val drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()

        val pedestrianRouter = TransportFactory.getInstance().createPedestrianRouter()

        val drivingOptions = DrivingOptions().apply {
            routesCount = 1
        }

        val pedestrianRouteListener = object : RouteListener {
            override fun onMasstransitRoutes(walkRoutes: MutableList<Route>) {

                val mapObjects = mapView.mapWindow.map.mapObjects

                mapObjects.clear()
                mapObjects.addCollection()

                val route = walkRoutes[0].geometry

                val line = mapObjects.addPolyline(route)

                line.dashLength = 20f
                line.gapLength = 10f
                line.setStrokeColor(R.color.main_blue)
                line.strokeWidth = 5f

                viewModel.updateNavigationWalkingTime(walkRoutes[0].metadata.weight.time.text)
                viewModel.updateNavigationWalkingDistance(walkRoutes[0].metadata.weight.walkingDistance.text)
            }

            override fun onMasstransitRoutesError(p0: Error) {
                Log.d(TAG, "MASSTRANSIT_ROUTE_ERROR: $p0")
            }

        }

        val drivingRouteListener = object : DrivingSession.DrivingRouteListener {
            override fun onDrivingRoutes(drivingRoutes: MutableList<DrivingRoute>) {

                if (drivingRoutes.isNotEmpty()
                ) {

                    val mapObjects = mapView.mapWindow.map.mapObjects

                    mapObjects.clear()
                    mapObjects.addCollection()

                    val route = drivingRoutes[0].geometry

                    val line = mapObjects.addPolyline(route)

                    line.dashLength = 20f
                    line.gapLength = 10f
                    line.setStrokeColor(Color.BLUE)
                    line.strokeWidth = 5f

                    viewModel.updateNavigationDrivingTime(drivingRoutes[0].metadata.weight.time.text)
                    viewModel.updateNavigationDrivingDistance(drivingRoutes[0].metadata.weight.distance.text)
                }
            }

            override fun onDrivingRoutesError(p0: com.yandex.runtime.Error) {
                Log.d(TAG, "DRIVING_ROUTE_ERROR: $p0")
            }
        }

        VTBTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = VTBTheme.backgroundColors.primary
            ) {

                if (isModalSheetForNavigationOpen) {

                    MapNavigationBottom(
                        onDismissRequest = {
                            isModalSheetForNavigationOpen = false
                            isNavigationStarted = false

                            val mapObjects = mapView.mapWindow.map.mapObjects

                            mapObjects.clear()
                            mapObjects.addCollection()

                                           },
                        modalStateForNavigation = modalStateForNavigation,
                        onStartWalking = {

                            viewModel.updateNavigationType(1)

                            isNavigationStarted = true
                            isModalSheetForNavigationOpen = false

                            val points = buildList {
                                add(
                                    RequestPoint(
                                        Point(
                                            currentPosition.latitude,
                                            currentPosition.longitude
                                        ), RequestPointType.WAYPOINT, "", ""
                                    )
                                )
                                add(
                                    RequestPoint(
                                        Point(
                                            chosenOffice?.latitude!!,
                                            chosenOffice.longitude
                                        ), RequestPointType.WAYPOINT, "", ""
                                    )
                                )
                            }

                            pedestrianRouter.requestRoutes(
                                points,
                                TimeOptions(),
                                pedestrianRouteListener
                            )
                        },
                        onStartDriving = {

                            viewModel.updateNavigationType(0)

                            isNavigationStarted = true
                            isModalSheetForNavigationOpen = false

                            val points = buildList {
                                add(
                                    RequestPoint(
                                        Point(
                                            currentPosition.latitude,
                                            currentPosition.longitude
                                        ), RequestPointType.WAYPOINT, "", ""
                                    )
                                )
                                add(
                                    RequestPoint(
                                        Point(
                                            chosenOffice?.latitude!!,
                                            chosenOffice.longitude
                                        ), RequestPointType.WAYPOINT, "", ""
                                    )
                                )
                            }

                            drivingRouter.requestRoutes(
                                points,
                                drivingOptions,
                                VehicleOptions(),
                                drivingRouteListener
                            )
                        },
                        office = chosenOffice!!,
                        drivingTime = viewModel.navigationDrivingTime,
                        drivingDistance = viewModel.navigationDrivingDistance,
                        walkingDistance = viewModel.navigationWalkingDistance,
                        walkingTime = viewModel.navigationWalkingTime
                    )
                }

                BottomSheetScaffold(
                    scaffoldState = scaffoldStateForOffices,
                    containerColor = VTBTheme.backgroundColors.primary,
                    sheetContainerColor = VTBTheme.backgroundColors.primary,
                    sheetPeekHeight = 50.dp,
                    sheetContent = {

                        //region BottomSheet for OFFICES LIST

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.8f)
                                .background(VTBTheme.backgroundColors.primary)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .horizontalScroll(scrollServicesState),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Spacer(
                                    modifier = Modifier
                                        .width(16.dp)
                                )

                                Button(
                                    onClick = {
                                        isFilterModalSheetOpen = true
                                    },
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier
                                        .fillMaxHeight(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = VTBTheme.backgroundColors.secondary
                                    ),
                                    shape = VTBTheme.shape.cornersStyle
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_settings),
                                        contentDescription = null
                                    )
                                }

                                Spacer(
                                    modifier = Modifier
                                        .width(8.dp)
                                )

                                servicesList?.forEachIndexed { index, name ->
                                    OutlinedButton(
                                        onClick = {
                                            viewModel.updateSelectedService(name)
                                        },
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            containerColor = if (viewModel.selectedService == name) VTBTheme.backgroundColors.accent else VTBTheme.backgroundColors.primary
                                        ),
                                        border = BorderStroke(1.dp, VTBTheme.strokeColors.accent)
                                    ) {

                                        Text(
                                            text = name,
                                            color = if (viewModel.selectedService == name) VTBTheme.textColors.contrast else VTBTheme.textColors.accent,
                                            fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                                            fontSize = 14.sp
                                        )
                                    }

                                    Spacer(
                                        modifier = Modifier
                                            .width(8.dp)
                                    )

                                }

                                Spacer(
                                    modifier = Modifier
                                        .width(8.dp)
                                )

                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 8.dp,
                                    )
                                    .verticalScroll(itemDepartmentColumnState)
                            ) {

                                officeList?.forEachIndexed { index, bankOffice ->
                                    Log.d("BankOffice", bankOffice.toString())
                                    DepartmentItem(
                                        bankOffice = bankOffice,
                                        onClick = {
                                            viewModel.updateChosenOffice(bankOffice)
                                            isDepartmentInfoModalSheetOpen = true
                                        }
                                    )

                                    Spacer(
                                        modifier = Modifier
                                            .height(8.dp)
                                    )
                                }
                            }
                        }

                        //endregion

                        //region BottomSheet for FILTERS

                        if (isFilterModalSheetOpen) {

                            ModalBottomSheet(
                                onDismissRequest = {
                                    isFilterModalSheetOpen = false
                                },
                                sheetState = filterModalSheetState,
                                containerColor = VTBTheme.backgroundColors.primary
                            ) {

                                Column {
                                    Text(
                                        text = stringResource(id = R.string.filters_departments),
                                        color = VTBTheme.textColors.primary,
                                        fontFamily = VTBTheme.typography.robotoMedium.fontFamily,
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .padding(
                                                start = 16.dp
                                            )
                                    )

                                    val filterList: List<String> = listOf(
                                        stringResource(id = R.string.for_people),
                                        stringResource(id = R.string.for_own_busy),
                                        stringResource(id = R.string.for_small_and_big_business),
                                        stringResource(id = R.string.for_huge_business),
                                    )

                                    Spacer(
                                        modifier = Modifier
                                            .height(18.dp)
                                    )

                                    filterList.forEachIndexed { index, filterName ->

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(
                                                    start = 16.dp,
                                                    end = 16.dp,
                                                ),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = filterName,
                                                fontSize = 16.sp,
                                                fontFamily = VTBTheme.typography.robotoRegular.fontFamily,
                                                color = VTBTheme.textColors.primary,
                                            )
                                            RadioButton(
                                                selected = (index == viewModel.selectedFilterIndex),
                                                onClick = {
                                                    Log.d("RV", index.toString())
                                                    viewModel.updateSelectedFilterIndex(index)
                                                    viewModel.updateSelectedService("Все")
                                                },
                                                colors = RadioButtonDefaults.colors(
                                                    selectedColor = VTBTheme.strokeColors.accent,
                                                    unselectedColor = VTBTheme.strokeColors.accent,
                                                )
                                            )
                                        }

                                        Spacer(
                                            modifier = Modifier
                                                .height(12.dp)
                                        )
                                    }

                                    Spacer(
                                        modifier = Modifier
                                            .height(30.dp)
                                    )
                                }
                            }
                        }

                        //endregion

                        //region BottomSheet for OFFICE INFO

                        if (isDepartmentInfoModalSheetOpen) {

                            ModalBottomSheet(
                                onDismissRequest = {
                                    isDepartmentInfoModalSheetOpen = false
                                },
                                sheetState = departmentInfoModalSheetState,
                                containerColor = VTBTheme.backgroundColors.primary
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.9f)
                                ) {

                                    if (chosenOffice != null) {
                                        DepartmentInfo(
                                            bank = chosenOffice,
                                            doRoute = {
                                                isModalSheetForNavigationOpen = true

                                                val points = buildList {
                                                    add(
                                                        RequestPoint(
                                                            Point(
                                                                currentPosition.latitude,
                                                                currentPosition.longitude
                                                            ), RequestPointType.WAYPOINT, "", ""
                                                        )
                                                    )
                                                    add(
                                                        RequestPoint(
                                                            Point(
                                                                chosenOffice?.latitude!!,
                                                                chosenOffice.longitude
                                                            ), RequestPointType.WAYPOINT, "", ""
                                                        )
                                                    )
                                                }

                                                drivingRouter.requestRoutes(
                                                    points,
                                                    drivingOptions,
                                                    VehicleOptions(),
                                                    drivingRouteListener
                                                )

                                                pedestrianRouter.requestRoutes(
                                                    points,
                                                    TimeOptions(),
                                                    pedestrianRouteListener
                                                )

                                            }
                                        )
                                    }
                                }
                            }
                        }

                        //endregionF
                    }
                ) {

                    val userLocation = viewModel.getUserLocation.observeAsState().value
                    val placeMarks = viewModel.getCurrentOfficesList.observeAsState().value

                    val mapObjectTapListener =
                        MapObjectTapListener { mapObject, point ->
                            val placeMarkerObject = mapObject as PlacemarkMapObject
                            viewModel.selectOfficeByCords(
                                placeMarkerObject.geometry.latitude,
                                placeMarkerObject.geometry.longitude
                            )
                            isDepartmentInfoModalSheetOpen = true
                            return@MapObjectTapListener false
                        }

                    val _this = this

                    Column(

                    ) {

                        if (isNavigationStarted) {

                            NavigatorItem(
                                if (viewModel.navigationType == 0) viewModel.navigationDrivingDistance else viewModel.navigationWalkingDistance,
                                if (viewModel.navigationType == 0) viewModel.navigationDrivingTime else viewModel.navigationWalkingTime,
                                doClose = {
                                    isNavigationStarted = false

                                    val mapObjects = mapView.mapWindow.map.mapObjects
                                    mapObjects.clear()
                                    mapObjects.addCollection()
                                }
                            )
                        }

                        AndroidView(
                            modifier = Modifier.fillMaxSize(),
                            factory = { context ->

                                val layout = LayoutInflater.from(context)
                                    .inflate(R.layout.map_screen, null, false)

                                mapView = layout.findViewById(R.id.mapview)

                                val mapKitFactoryInstance = MapKitFactory.getInstance()

                                userLocationLayer =
                                    mapKitFactoryInstance.createUserLocationLayer(mapView.mapWindow)
                                userLocationLayer.isVisible = true
                                userLocationLayer.isHeadingEnabled = false
                                userLocationLayer.setObjectListener(_this)

                                val goToLocationButton = layout.findViewById<ImageButton>(R.id.location)
                                goToLocationButton.setOnClickListener {
                                    userLocationLayer.cameraPosition()?.target?.let { it1 ->
                                        moveToLocation(
                                            mapView,
                                            it1
                                        )
                                    }
                                }

                                mapKitFactoryInstance.onStart()
                                mapView.onStart()

                                val tapListener = GeoObjectTapListener { geoObjectTapEvent ->
                                    val selectionMetadata: GeoObjectSelectionMetadata =
                                        geoObjectTapEvent
                                            .geoObject
                                            .metadataContainer
                                            .getItem(GeoObjectSelectionMetadata::class.java)
                                    mapView.map.selectGeoObject(selectionMetadata)
                                    false
                                }

                                mapView.map.addTapListener(tapListener)

                                if (mapView.parent != null) {
                                    (mapView.parent as ViewGroup).removeView(mapView)
                                }


                                mapView
                            }
                        ) { mapView ->

                            _this.mapView = mapView
                            Log.d(TAG, "ANDROID_VIEW UPDATED")

                            placeMarks?.forEach {
                                val placeMarkObject = mapView.map.mapObjects.addPlacemark(
                                    it.toPoint(),
                                    ImageProvider.fromResource(
                                        activityContext,
                                        R.drawable.ic_office
                                    )
                                )
                                placeMarkObject.addTapListener(mapObjectTapListener)
                            }

                            if (userLocation != null) {
                                val point = Point(userLocation.latitude, userLocation.longitude)
                                if (count == 0) {
                                    mapToLocation(mapView, point)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun mapToLocation(mapView: MapView, point: Point) {
        moveToLocation(
            mapView,
            point
        )
        count++
    }
}
