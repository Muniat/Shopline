package com.example.userregistration.Model3D;

public class ModelController {
    public String ASSET_3D = "";
    private String root = "http://ec10a525.ngrok.io";
    public ModelController(String ASSET_3D) {
        this.ASSET_3D = ASSET_3D;
    }

    public String getASSET_3D(String name) {
        if(ASSET_3D.equals("Truck")) {
            name = root + "/truck/model.gltf";
        }
        else if(ASSET_3D.equals("Battery")){
            name = root + "/battery/Battery.gltf";
        }
        else if(ASSET_3D.equals("Hand Drum")){
            name = root + "/handDrum/Hand drum.gltf";
        }
        else if(ASSET_3D.equals("Scientific Calculator")){
            name = root + "/scientificCalculator/Calculator_1314.gltf";
        }
        else if(ASSET_3D.equals("Black Jersey")){
            name =  "no";
        }
        else if(ASSET_3D.equals("Wooden Hanger")){
            name = root + "/woodenHanger/model.gltf";
        }
        else if(ASSET_3D.equals("Sofa set")){
            name = root + "sofaSet/model.gltf";
        }
        else if(ASSET_3D.equals("Fan")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Cable")){
            name = root + "/cable/jackobj.gltf";
        }
        else if(ASSET_3D.equals("Yamaha Bike")){
            name = root + "/yamahaBike/1388 Motorcycle.gltf";
        }
        else if(ASSET_3D.equals("Chest")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Table Tennis Bat")){
            name = root + "/tableTennisBat/Table tennisi paddle.gltf";
        }
        else if(ASSET_3D.equals("Nike Shoe")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Google Pixel 3xl")){
            name = root + "";
        }
        else if(ASSET_3D.equals("Fire Extinguisher")){
            name = root + "/googlePixel3XL/model_Google Pixel_20171103_101730454.gltf";
        }
        else if(ASSET_3D.equals("Mendalorian")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Lenevo Tablet")){
            name = root + "/lenevoTablet/Tablet_1350.gltf";
        }
        else if(ASSET_3D.equals("Arduino Uno")){
            name = "/arduinoUno/arduino.gltf";
        }
        else if(ASSET_3D.equals("Vest")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Hammar")){
            name = root + "/hammer/model.gltf";
        }
        else if(ASSET_3D.equals("Basic Table")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Wooden Bookshelf")){
            name = root + "/woodenBookshelf/model.gltf";
        }
        else if(ASSET_3D.equals("Window Blinds")){
            name = root + "/windowBlinds/model.gltf";
        }
        else if(ASSET_3D.equals("Gaming Pc")){
            name = root + "/gamingPC/model.gltf";
        }
        else if(ASSET_3D.equals("Flat Tv")){
            name = root + "/flatTv/model.gltf";
        }
        else if(ASSET_3D.equals("Drawer (Dresser)")){
            name = root + "/drawer/model.gltf";
        }
        else if(ASSET_3D.equals("Planet Amaris (3D Artwork")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Alarm Clock")){
            name = root + "/alarmClock/model.gltf";
        }
        else if(ASSET_3D.equals("Wooden Tissus Box")){
            name =  root + "/WoodenTissue/model.gltf";
        }
        else if(ASSET_3D.equals("Guitar Amp")){
            name = root + "/guitarAmp/model.gltf";
        }
        else if(ASSET_3D.equals("Microscope (Digital)")){
            name = root + "/microscope/model.gltf";
        }
        else if(ASSET_3D.equals("Blue Jersey")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Bowling Pin")){
            name = root + "/bowlingPin/Bowling_Pin.gltf";
        }
        else if(ASSET_3D.equals("Interface Screen")){
            name = root + "/interfaceScreen/AZCockpitInterfaceScreen.gltf";
        }
        else if(ASSET_3D.equals("Flask")){
            name = root + "/flast/model.gltf";
        }
        else if(ASSET_3D.equals("Saw")){
            name = root + "/saw/model.gltf";
        }
        else if(ASSET_3D.equals("Midi Controller")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("XR Wings (3d Artwork)")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("Pro Young Tennis Ball")){
            name = root + "/proYoungTennisBall/Tennis_Ball.gltf";
        }
        else if(ASSET_3D.equals("Flute (Rokon Flutes Bangladesh)")){
            name = root + "/flute/Flute_01.gltf";
        }
        else if(ASSET_3D.equals("Bedside Lamp")){
            name = root + "/bedsideLamp/model.gltf";
        }
        else if(ASSET_3D.equals("Macbook Pro Laptop")){
            name = root + "no";
        }
        else if(ASSET_3D.equals("ChairC Comfortable Sofa")){
            name = root + "/chairC/Chair1.gltf";
        }
        return name;
    }

    }


