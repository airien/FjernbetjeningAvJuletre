/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.politiet.soip.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import no.politiet.soip.PlantListFragment
import no.politiet.soip.data.Plant
import no.politiet.soip.data.PlantRepository

/**
 * The ViewModel for [PlantListFragment].
 */
class PlantListViewModel internal constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val growZoneNumber = MutableLiveData<Int>()

    private val plantList = MediatorLiveData<List<Plant>>()

    init {
        growZoneNumber.value = NO_GROW_ZONE

        val livePlantList = Transformations.switchMap(growZoneNumber) {
            if (it == NO_GROW_ZONE) {
                plantRepository.getPlants()
            } else {
                plantRepository.getPlantsWithGrowZoneNumber(it)
            }
        }
        plantList.addSource(livePlantList, plantList::setValue)
    }

    fun getPlants() = plantList

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
    }
}
