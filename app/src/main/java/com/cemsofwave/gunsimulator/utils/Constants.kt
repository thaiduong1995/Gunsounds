package com.cemsofwave.gunsimulator.utils

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 13/10/2023
 */

const val VIEW_SPLASH_ACTIVITY = "splash"

//SHOW
const val MAIN_SHOW = "main"
const val TAB_GUN_SHOW = "tab_gun"
const val GUN_FAVORITE_SHOW = "gun_favorite"
const val GUN_DETAIL_SHOW = "gun_detail"
const val TAB_TASER_SHOW = "tab_taser"
const val TAB_BOMB_SHOW = "tab_bomb"
const val TASER_FAVORITE_SHOW = "taser_favorite"
const val TASER_DETAIL_SHOW = "taser_detail"
const val ADD_DETAIL_SHOW = "add_detail"
const val TAB_SABER_SHOW = "tab_saber"
const val SABER_FAVORITE_SHOW = "saber_favorite"
const val SABER_DETAIL_SHOW = "saber_detail"
const val BOMB_FAVORITE_SHOW = "bomb_favorite"
const val BOMB_GRENADE_SHOW = "bomb_grenade"
const val BOMB_TIMER_SHOW = "bomb_timer"

const val VIEW_COLLECTION_ACTIVITY = "collection"
const val VIEW_SETTING = "setting"
const val VIEW_POLICY = "policy"

//EVENT CLICK
const val CLICK_CLOSE = "close"

//MAIN
const val CLICK_BACK = "back"
const val CLICK_BACKGROUND = "background"
const val MAIN_CLICK_GUN = "gun"
const val MAIN_CLICK_TASER = "taser"
const val MAIN_CLICK_SABER = "saber"
const val MAIN_CLICK_EXPLOSION = "bomb"
const val MAIN_CLICK_SETTING = "setting"
const val CLICK_NEXT = "next"
const val CLICK_PREVIOUS = "previous"

//SETTING
const val SETTING_CLICK_SHARE = "share"
const val SETTING_CLICK_RATE = "rate"
const val SETTING_CLICK_LANGUAGE = "language"
const val SETTING_CLICK_FEEDBACK = "feedback"
const val SETTING_CLICK_POLICY = "policy"

//GUN
const val GUN_NAME = "gun_name"
const val CLICK_FAVOURITE = "favourite"
const val CLICK_UN_FAVOURITE = "un_favorites"
const val GUN_CLICK_GUN = "gun"

//GUN_DETAIL
const val GUN_DETAIL = "gun_detail"
const val GUN_INFO = "info"
const val GUN_DETAIL_CLICK_BG = "bg"
const val CLICK_FULLSCREEN = "full_screen"
const val CLICK_SETTING = "setting"
const val CLICK_RELOAD = "reload"
const val SELECT_MODE = "select_mode"
const val MODE = "mode"
const val DONE = "done"

const val CLICK_GUN = "click_gun"
const val CLICK_SINGLE = "single"
const val CLICK_BRUSH = "brush"
const val CLICK_AUTO = "auto"
const val CLICK_SHAKE = "shake"

//TASER
const val TASER_CLICK_TASER = "taser"
const val TASER_NAME = "taser_name"

//TASER_DETAIL
const val TASER_DETAIL = "taser_detail"
const val CLICK_TASER = "click_taser"

//SABER
const val SABER_CLICK_SABER = "saber"
const val SABER_NAME = "saber_name"

//SABER_DETAIL
const val SABER_DETAIL = "saber_detail"
const val CLICK_SABER = "click_saber"
const val SHAKE_SABER = "shake_saber"

//Bomb
const val BOMB_CLICK_GRENADE = "bomb_click_grenade"
const val BOMB_CLICK_TIMER = "bomb_click_timer"
const val BOMB_NAME = "bomb_name"
const val CLICK_GRENADE = "click_grenade"
const val CLICK_TIMER = "click_timer"
const val BOMB_GRENADE = "bomb_grenade"
const val BOMB_TIMER = "bomb_timer"
const val SHAKE_BOMB = "shake_bomb"
const val BROKEN = "broken"
const val CLICK_BROKEN = "click_broken"

//GENERAL
const val SKIN_ID = "skin_id"
const val SELECT_SKIN = "select_skin"
const val BACKGROUND_NAME = "background_name"
const val SELECT_BACKGROUND = "select_background"
const val CLICK_CHARGE = "charge"
const val SETTING_TOGGLE_VIBRATE = "setting_toggle_vibrate"
const val SETTING_TOGGLE_FLASH = "setting_toggle_flash"
const val SETTING_TOGGLE_SOUND = "setting_toggle_sound"
const val ADJUST_COLOR = "adjust_color"

const val CLICK_EXIT_FULLSCREEN = "exit_full_screen"
const val CLICK_ZOOM = "zoom"
const val CLICK_ITEM_SKIN = "ITEM_SKIN"
const val CHECK_LOG = "CHECK_LOG"
const val ERROR = "ERROR"
const val CHARGE_BATTERY_DIALOG =  "charge_battery_dialog"
const val BULLETS_REQUEST_DIALOG = "bullets_request_dialog"
const val GRENADE_REQUEST_DIALOG = "grenades_request_dialog"
const val LANGUAGE_DIALOG = "language"
const val SKIN_DIALOG = "skin_dialog"
const val NETWORK_DIALOG = "network_dialog"
const val Error = "Error"
enum class SimulatorType {
    GUN,SHOCK_TASER,LIGHT_SABER,EXPLOSION
}

enum class GunShootMode{
    SINGLE,BRUSH,AUTO,SHAKE
}

const val IS_VIBRATE_DEF = true
const val IS_FLASH_DEF = true
const val IS_SOUND_DEF = true
var isShowNetwork = true